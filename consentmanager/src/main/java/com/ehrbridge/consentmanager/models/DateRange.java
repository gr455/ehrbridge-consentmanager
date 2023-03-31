package com.ehrbridge.consentmanager.models;

import java.util.Date;

public class DateRange {

    public Date from;
    public Date to;

    public DateRange(Date dateFrom, Date dateThrough) {
        this.from = dateFrom;
        this.to = dateThrough;
    }
}