package com.ehrbridge.consentmanager.dao;

import com.ehrbridge.consentmanager.models.ConsentObject;
import com.ehrbridge.consentmanager.models.ConsentPermission;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ConsentDataAccess {
    public UUID insertConsentObject(ConsentObject consentObject) { return UUID.randomUUID(); };

    public ConsentObject getConsentObject(UUID consentID) { return null; };

    public int updateConsentObject(ConsentObject consentObject) { return 1; };
}
