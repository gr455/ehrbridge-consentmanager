package com.ehrbridge.consentmanager.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ehrbridge.consentmanager.dao.ConsentDataAccess;
import com.ehrbridge.consentmanager.helpers.HTTPHelper;
import com.ehrbridge.consentmanager.models.ConsentObject;
import com.ehrbridge.consentmanager.models.ConsentRequest;
import com.ehrbridge.consentmanager.models.Constants;
import com.ehrbridge.consentmanager.models.SignedConsentObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

@Service
public class ConsentService {
    private final ConsentDataAccess consentDataAccess;

    @Autowired
    public ConsentService(ConsentDataAccess consentDataAccess) {
        this.consentDataAccess = consentDataAccess;
    }

    public UUID addConsentObject(ConsentObject consentObject) {
        return this.consentDataAccess.insertConsentObject(consentObject);
    }

    public ConsentObject getConsentObject(UUID consentID) {
        return this.consentDataAccess.getConsentObject(consentID);
    }

    public int updateConsentObject(ConsentObject consentObject) {
        return this.consentDataAccess.updateConsentObject(consentObject);
    }

    // Dispatch consent request to the Patient server
    public boolean dispatchConsentRequest(ConsentRequest consentRequest) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            System.out.println(consentRequest.txnID);
            System.out.println(consentRequest.requestDetails);
            System.out.println(consentRequest.consent_obj);
//            String bodyJSON = ow.writeValueAsString(consentRequest);
            HTTPHelper http = new HTTPHelper();
//            http.post(Constants.PATIENT_SERVER_HOST + Constants.PATIENT_SERVER_CONSENT_ENDPOINT, bodyJSON);
//            return http.getStatus().value() == 200;
            return true;
        } catch (Exception e) {
            System.out.println("[ConsentManager] ERR: failed to serialize consent request");
            e.printStackTrace();
        }

        return false;
    }

    // Send notification to Gateway about update in consent status
    // Also sends the signed consent object
    public boolean dispatchConsentUpdate(ConsentRequest consentRequest) {

        ConsentObject consentObject = consentRequest.consent_obj;
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String payload = "{}";
        try {
            payload = ow.writeValueAsString(consentObject);
        } catch (JsonProcessingException e) {
            System.out.println("[ConsentManager] ERR: failed to serialize signed consent object");
            e.printStackTrace();
            return false;
        }

        // Construct the signed payload
        String jwt = this.signConsentObject(payload);
        // Serialize this JWT into json
        SignedConsentObject sco = new SignedConsentObject(consentRequest.txnID, consentObject.consent_status, jwt);

        try {
            String scoJSON = ow.writeValueAsString(sco);
            HTTPHelper http = new HTTPHelper();
            System.out.println(scoJSON);
            http.post(Constants.GATEWAY_HOST + Constants.GATEWAY_CONSENT_ENDPOINT, scoJSON);
            return http.getStatus().value() == 200;
        } catch (JsonProcessingException e) {
            System.out.println("[ConsentManager] ERR: failed to serialize signed consent object");
            e.printStackTrace();
        }

        return false;
    }

    private String signConsentObject(String stringPayload) {

        Algorithm algorithm = Algorithm.RSA256(Constants.RSA_PUB, Constants.RSA_PRIV);

        String signedConsentObject = JWT.create()
                                        .withClaim("consent_obj", stringPayload)
                                        .sign(algorithm);
        return signedConsentObject;
    }

}
