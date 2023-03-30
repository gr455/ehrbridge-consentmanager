package com.ehrbridge.consentmanager.models;

import com.ehrbridge.consentmanager.helpers.RSAHelper;

import java.util.UUID;

/*
 * ConsentRequestResponse defines the type of response sent by Consent Manager to Gateway in response
 * to a consent request
 * */
public class ConsentRequestResponse {
    public UUID consentID;

    public ConsentRequestResponse(UUID consentID) {
        this.consentID = consentID;
    }
}
