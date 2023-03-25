package com.ehrbridge.consentmanager.models;

import java.util.UUID;

/*
 * SignedConsentObject is the consent object in the form of signed JWT.
 * The payload of the JWT is the serialized ConsentObject object
 **/
public class SignedConsentObject {
    UUID consentID;
    String consentStatus;
    String consentJWT; // serialized ConsentObject

    public SignedConsentObject(UUID consentID, String consentStatus, String consentJWT) {
        this.consentID = consentID;
        this.consentStatus = consentStatus;
        this.consentJWT = consentJWT;
    }
}
