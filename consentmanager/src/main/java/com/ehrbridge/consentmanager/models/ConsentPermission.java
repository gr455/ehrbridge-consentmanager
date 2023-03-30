package com.ehrbridge.consentmanager.models;

import java.util.Date;

public class ConsentPermission {
    public DateRange dateRange;

    public Date consent_validity;

    public ConsentPermission(Date dateFrom, Date dateThrough, Date consentValidity) {
        this.consent_validity = consentValidity;
        this.dateRange = new DateRange(dateFrom, dateThrough);
    }
}

class DateRange {

    public Date from;
    public Date to;

    public DateRange(Date dateFrom, Date dateThrough) {
        this.from = dateFrom;
        this.to = dateThrough;
    }
}