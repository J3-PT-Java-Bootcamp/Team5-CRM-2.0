package com.ironhack.team5crm.controllers;

import com.ironhack.team5crm.models.SalesRep;
import com.ironhack.team5crm.services.servicesInterfaces.SalesRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/salesreps")
public class SalesRepController {

    @Autowired
    @Qualifier("data")
    private SalesRepService salesRepService;


    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<SalesRep> total(){
        return salesRepService.findAllSalesRep();
    }


    //“Report Lead by SalesRep”. The CRM displays the total number of Leads owned by each SalesRep:
    @GetMapping("/leads")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Object[]> getLeads(){
        return salesRepService.leadersBySalesRep();
    }
}
