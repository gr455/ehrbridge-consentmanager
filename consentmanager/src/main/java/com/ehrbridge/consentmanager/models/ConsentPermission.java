package com.ehrbridge.consentmanager.models;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;

public class ConsentPermission {
    public DateRange dateRange;
    public LocalDateTime consentValidity;

    public ConsentPermission(LocalDateTime dateFrom, LocalDateTime dateThrough, LocalDateTime consentValidity) {
        this.consentValidity = consentValidity;
        this.dateRange = new DateRange(dateFrom, dateThrough);
    }
}

class DateRange {
    public LocalDateTime dateFrom;
    public LocalDateTime dateThrough;

    public DateRange(LocalDateTime dateFrom, LocalDateTime dateThrough) {
        this.dateFrom = dateFrom;
        this.dateThrough = dateThrough;
    }
}