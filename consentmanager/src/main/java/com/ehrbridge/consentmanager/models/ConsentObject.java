package com.ehrbridge.consentmanager.models;

import java.util.UUID;

/*
 * ConsentObject defines the type of consent object stored in the database. This is also the consent_object sent around
 * the different entities of EHBr
 * */
public class ConsentObject {
    public final UUID consentID;
    public UUID ehbrID;
    public UUID hiuID;
    public UUID hipID;
    public UUID doctorID;
    public String[] hiType;
    public String[] departments;
    public String consentDescription;
    public ConsentPermission permission;
    public String consentStatus;

    // Generates a random UUID for consentID and makes the object
    public ConsentObject(UUID ehbrID, UUID hiuID, UUID hipID, UUID doctorID, String[] hiType, String[] departments, String consentDescription, ConsentPermission permission, String consentStatus) {
        this.consentID = UUID.randomUUID();
        this.ehbrID = ehbrID;
        this.hiuID = hiuID;
        this.hipID = hipID;
        this.doctorID = doctorID;
        this.hiType = hiType;
        this.departments = departments;
        this.consentDescription = consentDescription;
        this.permission = permission;
        this.consentStatus = consentStatus;
    }
}
