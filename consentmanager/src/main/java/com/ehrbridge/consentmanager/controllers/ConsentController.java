package com.ehrbridge.consentmanager.controllers;

import com.ehrbridge.consentmanager.dao.ConsentDataAccess;
import com.ehrbridge.consentmanager.models.ConsentObject;
import com.ehrbridge.consentmanager.models.ConsentPermission;
import com.ehrbridge.consentmanager.models.ConsentRequestResponse;
import com.ehrbridge.consentmanager.services.ConsentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/consent")
@RestController
public class ConsentController {
     private final ConsentService consentService;

     @Autowired
     public ConsentController(ConsentService consentService) {
         this.consentService = consentService;
     }

     @RequestMapping(value = "/request", method = RequestMethod.POST)
     @ResponseBody
     public ConsentRequestResponse createConsentRequest(ConsentObject consentObject) {
         // Set consent status to pending
         consentObject.consentStatus = "PENDING";

         // Add consent object to DB
         UUID consentID = this.consentService.addConsentObject(consentObject);

         // Fire the consent request to the patient server
         this.consentService.dispatchConsentRequest(consentObject);

         // Return consentID and RSA Public Key as a response
         ConsentRequestResponse resp = new ConsentRequestResponse(consentID);
         return resp;

     }

     @RequestMapping(value = "/notify-status", method = RequestMethod.POST)
     @ResponseBody
     public void notifyStatusHook(ConsentObject consentObject) {
         // Get stored consent object
         ConsentObject storedConsentObject = this.consentService.getConsentObject(consentObject.consentID);

         // Update consent status to REJECTED or GRANTED
         storedConsentObject.consentStatus = consentObject.consentStatus;

         // Update the consent object in DB
         this.consentService.updateConsentObject(storedConsentObject);

         // Fire notification to Gateway about the update in consent status
         this.consentService.dispatchConsentUpdate(storedConsentObject);
     }


}
