package com.ehrbridge.consentmanager.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsentRequestPS {
    public UUID txnID;
    public RequestDetails request_details;
    public ConsentObject consent_obj;
    public String callback_url;
}
