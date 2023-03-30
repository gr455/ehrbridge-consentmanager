package com.ehrbridge.consentmanager.models;

import com.ehrbridge.consentmanager.helpers.RSAHelper;

import java.util.UUID;

/*
 * SignedConsentObject is the consent object in the form of signed JWT.
 * The payload of the JWT is the serialized ConsentObject object
 **/
public class SignedConsentObject {
    public UUID txnId;
    public String consent_status;
    public String consent_obj; // serialized ConsentObject
    public String public_key;

    public SignedConsentObject(UUID txnId, String consentStatus, String consentJWT) {
        this.txnId = txnId;
        this.consent_status = consentStatus;
        this.consent_obj = consentJWT;
        this.public_key = RSAHelper.rsaPublicKeyObjectToPEM(Constants.RSA_PUB);
    }
}
