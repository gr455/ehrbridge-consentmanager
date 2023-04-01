package com.ehrbridge.consentmanager.models;

import org.apache.coyote.Request;

import java.util.UUID;

public class ConsentRequest {
    public UUID txnID;
    public RequestDetails requestDetails;
    public ConsentObject consent_obj;
}

class RequestDetails {
    public String hiuName;
    public String hipName;
    public String doctorName;

    RequestDetails(String hiuName, String hipName, String doctorName) {
        this.hiuName = hiuName;
        this.hipName = hipName;
        this.doctorName = doctorName;
    }
}