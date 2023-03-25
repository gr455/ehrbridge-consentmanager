package com.ehrbridge.consentmanager.models;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/*
 * Constants file used to define different constants used throughout this application
 **/
public class Constants {
    public static RSAPublicKey RSA_PUB;
    public static RSAPrivateKey RSA_PRIV;

    public static final String GATEWAY_HOST = "https://gateway-endpoint";
    public static final String PATIENT_SERVER_HOST = "https://patient-server-endpoint";
    public static final String PATIENT_SERVER_CONSENT_ENDPOINT = "/consent/receive";
    public static final String GATEWAY_CONSENT_ENDPOINT = "/api/v1/consent/revceive";
}
