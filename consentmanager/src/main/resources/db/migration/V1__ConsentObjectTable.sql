CREATE TABLE consent_object (
    consentID UUID NOT NULL PRIMARY KEY,
    ehbrID UUID,
    hiuID UUID,
    hipID UUID,
    doctorID UUID,
    hiType text[],
    departments text[],
    consentDescription text,
    consentStatus char(10),
    consentValidity date,
    consentDateFrom date,
    consentDateThrough date
)