package com.ehrbridge.consentmanager.models;

import java.lang.reflect.Field;
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
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }
}
