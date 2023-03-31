package com.ehrbridge.consentmanager.dao;

import com.ehrbridge.consentmanager.models.ConsentObject;
import com.ehrbridge.consentmanager.models.ConsentPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ConsentDataAccess {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ConsentDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public UUID insertConsentObject(ConsentObject consentObject) {
        final String sql = "INSERT INTO consent_object (consentid, ehbrid, hiuid, hipid, doctorid, hitype, departments, consentdescription, consentstatus, consentvalidity, consentdatefrom, consentdatethrough) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(
                sql,
                consentObject.consentID,
                consentObject.ehbrID,
                consentObject.hiuID,
                consentObject.hipID,
                consentObject.doctorID,
                consentObject.hiType,
                consentObject.departments,
                consentObject.consentDescription,
                consentObject.consentStatus,
                consentObject.permission.consent_validity,
                consentObject.permission.dateRange.from,
                consentObject.permission.dateRange.to
        );

        return consentObject.consentID;
    };

    public ConsentObject getConsentObject(UUID consentID) {
        final String sql = "SELECT * from consent_object where consentid = ?";
        ConsentObject consent = this.jdbcTemplate.queryForObject(sql, new Object[]{consentID}, (resultSet, i) -> {
            ConsentObject consentObject = new ConsentObject(
                    (UUID) resultSet.getObject("ehbrid"),
                    (UUID) resultSet.getObject("hiuid"),
                    (UUID) resultSet.getObject("hipid"),
                    (UUID) resultSet.getObject("doctorid"),
                    (String[]) resultSet.getArray("hitype").getArray(),
                    (String[]) resultSet.getArray("departments").getArray(),
                    resultSet.getString("consentdescription"),
                    new ConsentPermission(
                            resultSet.getDate("consentdatefrom"),
                            resultSet.getDate("consentdatethrough"),
                            resultSet.getDate("consentvalidity")
                    ),
                    resultSet.getString("consentstatus")
            );

            consentObject.consentID = (UUID) resultSet.getObject("consentid");

            return consentObject;

        });

        return consent;
    };

    public int updateConsentObject(ConsentObject consentObject) {
        final String sql = "UPDATE consent_object set consentstatus = ? WHERE consentid = ?";
        jdbcTemplate.update(sql, consentObject.consentStatus, consentObject.consentID);
        return 1;
    };
}
