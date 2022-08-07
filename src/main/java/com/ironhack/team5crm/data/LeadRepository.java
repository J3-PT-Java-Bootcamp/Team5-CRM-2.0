package com.ironhack.team5crm.data;

import com.ironhack.team5crm.data.datasources.Datasource;
import com.ironhack.team5crm.data.exceptions.DataNotFoundException;
import com.ironhack.team5crm.domain.Lead;

import java.util.List;

public class LeadRepository {

    private static LeadRepository instance;
    private final Datasource datasource;

    private LeadRepository(Datasource datasource) {
        this.datasource = datasource;
    }

    public static LeadRepository getInstance(Datasource datasource) {
        if (instance == null) {
            instance = new LeadRepository(datasource);
        }
        return instance;
    }

    public int maxLeadId() {
        return datasource.getMaxLeadId();
    }

    public Lead findById(int id) throws DataNotFoundException {
        var leads = getAllLeads();
        Lead leadFound = null;

        for (Lead lead : leads) {
            if (lead.getId() == id) {
                leadFound = lead;
                break;
            }
        }

        if (leadFound == null)
            throw new DataNotFoundException();

        return leadFound;
    }

    public Lead saveLead(Lead lead) {
        return datasource.saveLead(lead);
    }

    public List<Lead> getAllLeads() {
        return datasource.getAllLeads();
    }

    public void deleteAllLeads() {
        datasource.deleteAllLeads();
    }

    public void deleteLead(int id) throws DataNotFoundException {
        datasource.deleteLead(findById(id));
    }
}
