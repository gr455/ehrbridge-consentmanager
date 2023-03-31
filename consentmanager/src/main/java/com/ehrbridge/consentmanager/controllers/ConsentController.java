package com.ehrbridge.consentmanager.controllers;

import com.ehrbridge.consentmanager.dao.ConsentDataAccess;
import com.ehrbridge.consentmanager.models.ConsentObject;
import com.ehrbridge.consentmanager.models.ConsentPermission;
import com.ehrbridge.consentmanager.models.ConsentRequest;
import com.ehrbridge.consentmanager.models.ConsentRequestResponse;
import com.ehrbridge.consentmanager.services.ConsentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@RequestMapping("api/v1/consent")
@RestController
public class ConsentController {
     private final ConsentService consentService;

     @Autowired
     public ConsentController(ConsentService consentService) {
         this.consentService = consentService;
     }

     @RequestMapping(value = "/recieve", method = RequestMethod.POST)
     @ResponseBody
     public ConsentRequestResponse createConsentRequest(@RequestBody ConsentRequest consentRequest) {
         ConsentObject consentObject = consentRequest.consent_obj;
         // Set consent status to pending
         consentObject.consentStatus = "PENDING";
         // consentObject.permission = new ConsentPermission(new Date(), new Date(), new Date());

         // Add consent object to DB
         UUID consentID = this.consentService.addConsentObject(consentObject);

         // Fire the consent request to the patient server
         this.consentService.dispatchConsentRequest(consentRequest);

         // Return consentID and RSA Public Key as a response
         ConsentRequestResponse resp = new ConsentRequestResponse(consentID);
         return resp;

     }

     @RequestMapping(value = "/notify-status", method = RequestMethod.POST)
     @ResponseBody
     public void notifyStatusHook(@RequestBody ConsentRequest consentRequest) {
         // Get stored consent object
         ConsentObject consentObject = consentRequest.consent_obj;
         ConsentObject storedConsentObject = consentObject;// this.consentService.getConsentObject(consentObject.consentID);

         // Update consent status to REJECTED or GRANTED
         storedConsentObject.consentStatus = consentObject.consentStatus;

         // Update the consent object in DB
         this.consentService.updateConsentObject(storedConsentObject);

         // Fire notification to Gateway about the update in consent status
         consentRequest.consent_obj = storedConsentObject;
         this.consentService.dispatchConsentUpdate(consentRequest);
     }


}
