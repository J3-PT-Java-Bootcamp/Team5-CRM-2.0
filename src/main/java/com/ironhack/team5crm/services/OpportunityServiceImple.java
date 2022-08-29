package com.ironhack.team5crm.services;

import com.ironhack.team5crm.models.Opportunity;
import com.ironhack.team5crm.models.enums.Status;
import com.ironhack.team5crm.repositories.OpportunityRepository;
import com.ironhack.team5crm.services.exceptions.DataNotFoundException;
import com.ironhack.team5crm.services.exceptions.EmptyException;
import com.ironhack.team5crm.services.interfaceService.OpportunityService;
import com.ironhack.team5crm.ui.ConsoleOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpportunityServiceImple implements OpportunityService, ConsoleOperations{

    @Autowired
    private OpportunityRepository opportunityRepository;


    //***********************************************************************
    // * GENERAL METHODS
    //***********************************************************************
    /**
     * shows all Opportunities stored in the database or gives information if there
     * are no Opportunities in the Database
     */
    public List<Opportunity> getAll() throws EmptyException {
        var opportunities = opportunityRepository.findAll();
        if (opportunities.isEmpty()) {
            throw new EmptyException();
        } else {
            return opportunities;
        }
    }

    /**
     * shows requested opportunity by ID or gives information if the Opportunity
     * doesn't exist in the Database
     */
    public Opportunity lookUpOpportunity(int id) throws EmptyException, DataNotFoundException {
        if (opportunityRepository.findAll().size() == 0) {
            throw new EmptyException();
        } else {
            var oppToBeFound = opportunityRepository.findById(id);
            if (oppToBeFound.isEmpty()) {
                throw new DataNotFoundException();
            } else {
                return oppToBeFound.get();
            }
        }
    }
    //***********************************************************************
    // * METHODS TO CHANGE THE OPPORTUNITIES STATUS
    //***********************************************************************
    public Opportunity updateOpportunityStatus(int id, Status status) throws DataNotFoundException {
        var oppToBeFound = opportunityRepository.findById(id);
        if (oppToBeFound.isEmpty()) {
            throw new DataNotFoundException();
        } else {
            var opp = oppToBeFound.get();
            opp.setStatus(status);
            return opportunityRepository.save(opp);
        }
    }

    //***********************************************************************
    // BY PRODUCTS
    //***********************************************************************

    //A count of all Opportunities by the product
    @Override
    public Map<Object, Object> counterOpportunitiesByProduct(String product) throws EmptyException {
        List <Object[]> produc = opportunityRepository.countByProduct(product);
        Map <Object, Object> count = new HashMap<>();
        if ((produc.isEmpty())){
            throw new EmptyException();
        }else {
            for(int i = 0; i < produc.size(); i++){
                count.put(produc.get(i)[0], produc.get(i)[1]);
            }
        }
        return count;
    }

    @Override
    public Map<Object, Object> reportsByProducts(String stats, String products) throws EmptyException {

        Map <Object, Object> count = new HashMap<>();

        switch (stats){
            case OPEN -> {//A count of all OPEN Opportunities by the product

                List <Object[]> product = opportunityRepository.countByStatusAndProduct(OPEN, products);
                if ((product.isEmpty())){
                    throw new EmptyException();
                }else {
                    for(int i = 0; i < product.size(); i++){
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
            case CLOSED_LOST -> { //A count of all CLOSED_LOST Opportunities by the product

                List <Object[]> product = opportunityRepository.countByStatusAndProduct(CLOSE_LOST_TABLES, products);
                if ((product.isEmpty())){
                    throw new EmptyException();
                }else {
                    for(int i = 0; i < product.size(); i++){
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
            case CLOSED_WON -> { //A count of all CLOSED_WON Opportunities by the product

                List <Object[]> product = opportunityRepository.countByStatusAndProduct(CLOSE_WON_TABLES, products);
                if ((product.isEmpty())){
                    throw new EmptyException();
                }else {
                    for(int i = 0; i < product.size(); i++){
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
        }

        return count;
    }


    //***********************************************************************
    // BY COUNTRY
    //***********************************************************************

    //A count of all Opportunities by country
    @Override
    public Map<Object, Object> counterOpportunitiesByCountry(String country) throws EmptyException {
        List <Object[]> product = opportunityRepository.countByCountry(country);
        Map <Object, Object> count = new HashMap<>();
        if ((product.isEmpty())){
            throw new EmptyException();
        }else {
            for(int i = 0; i < product.size(); i++){
                count.put(product.get(i)[0], product.get(i)[1]);
            }
        }
        return count;
    }

    @Override
    public Map<Object, Object> reportByCountry(String stats, String countrys) throws EmptyException {

        Map <Object, Object> count = new HashMap<>();

        switch (stats){
            case CLOSED_WON -> {  //A count of all CLOSED_WON Opportunities by country

                List <Object[]> product = opportunityRepository.countByCountryByStatus(CLOSE_WON_TABLES, countrys);
                if ((product.isEmpty())){
                    throw new EmptyException();
                }else {
                    for(int i = 0; i < product.size(); i++){
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
            case CLOSED_LOST -> { //A count of all CLOSED_LOST Opportunities by country

                List <Object[]> product = opportunityRepository.countByCountryByStatus(CLOSE_LOST_TABLES, countrys);
                if ((product.isEmpty())){
                    throw new EmptyException();
                }else {
                    for(int i = 0; i < product.size(); i++){
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
            case OPEN -> { ////A count of all OPEN Opportunities by country

                List <Object[]> product = opportunityRepository.countByCountryByStatus(OPEN, countrys);
                if ((product.isEmpty())){
                    throw new EmptyException();
                }else {
                    for(int i = 0; i < product.size(); i++){
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
        }

        return count;
    }


    //***********************************************************************
    // BY City
    //***********************************************************************

    //A count of all Opportunities by country
    @Override
    public Map<Object, Object> counterOpportunitiesByCity(String city) throws EmptyException {
        List <Object[]> product = opportunityRepository.countByCity(city);
        Map <Object, Object> count = new HashMap<>();
        if ((product.isEmpty())){
            throw new EmptyException();
        }else {
            for(int i = 0; i < product.size(); i++){
                count.put(product.get(i)[0], product.get(i)[1]);
            }
        }
        return count;
    }

    @Override
    public Map<Object, Object> reportByCity(String stats, String city) throws EmptyException {

        Map <Object, Object> count = new HashMap<>();

        switch (stats){
            case CLOSED_WON -> {  //A count of all CLOSED_WON Opportunities by city

                List <Object[]> product = opportunityRepository.countByStatusAndCity(CLOSE_WON_TABLES, city);
                if ((product.isEmpty())){
                    throw new EmptyException();
                }else {
                    for(int i = 0; i < product.size(); i++){
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
            case CLOSED_LOST -> { //A count of all CLOSED_LOST Opportunities by city

                List <Object[]> product = opportunityRepository.countByStatusAndCity(CLOSE_LOST_TABLES, city);
                if ((product.isEmpty())){
                    throw new EmptyException();
                }else {
                    for(int i = 0; i < product.size(); i++){
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
            case OPEN -> { ////A count of all OPEN Opportunities by city

                List <Object[]> product = opportunityRepository.countByStatusAndCity(OPEN, city);
                if ((product.isEmpty())){
                    throw new EmptyException();
                }else {
                    for(int i = 0; i < product.size(); i++){
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
        }

        return count;
    }

    @Override
    public Map<Object, Object> counterOpportunitiesByIndustry(String industry) throws EmptyException {

        List <Object[]> product = opportunityRepository.countByIndustry(industry);
        Map <Object, Object> count = new HashMap<>();
        if ((product.isEmpty())){
            throw new EmptyException();
        }else {
            for(int i = 0; i < product.size(); i++){
                count.put(product.get(i)[0], product.get(i)[1]);
            }
        }
        return count;
    }

    @Override
    public Map<Object, Object> reportByIndustry(String stats, String industry) throws EmptyException {

        Map <Object, Object> count = new HashMap<>();

        switch (stats){
            case CLOSED_WON -> {  //A count of all CLOSED_WON Opportunities by Industry

                List <Object[]> product = opportunityRepository.countByIndustryStatus(CLOSE_WON_TABLES,  industry);
                if ((product.isEmpty())){
                    throw new EmptyException();
                }else {
                    for(int i = 0; i < product.size(); i++){
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
            case CLOSED_LOST -> { //A count of all CLOSED_LOST Opportunities by industry

                List <Object[]> product = opportunityRepository.countByIndustryStatus(CLOSE_LOST_TABLES,  industry);
                if ((product.isEmpty())){
                    throw new EmptyException();
                }else {
                    for(int i = 0; i < product.size(); i++){
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
            case OPEN -> { ////A count of all OPEN Opportunities by industry

                List <Object[]> product = opportunityRepository.countByIndustryStatus(OPEN,  industry);
                if ((product.isEmpty())){
                    throw new EmptyException();
                }else {
                    for(int i = 0; i < product.size(); i++){
                        count.put(product.get(i)[0], product.get(i)[1]);
                    }
                }
            }
        }

        return count;
    }

    @Override
    public int statesByOpportunity(String states, String value) {
        switch (states){
            case MEAN -> {
                switch (value){
                    case EMPLOYEE_COUNT -> {
                        return opportunityRepository.meanByEmployeeCount();
                    }
                    case OPPS -> {
                        return opportunityRepository.meanByAccountCount();
                    }
                    case QUANTITY -> {
                        return opportunityRepository.meanByQuantityCount();
                    }
                }
            }
            case MEDIAN -> {
                switch (value){
                    case EMPLOYEE_COUNT -> {
                        return opportunityRepository.medianByEmployeeCount();
                    }
                    case OPPS -> {
                        return opportunityRepository.medianByAccountCount();
                    }
                    case QUANTITY -> {
                        return opportunityRepository.medianByQuantityCount();
                    }
                }
            }
            case MIN -> {
                switch (value){
                    case EMPLOYEE_COUNT -> {
                        return opportunityRepository.minByEmployeeCount();
                    }
                    case OPPS -> {
                        return opportunityRepository.minByAccountCount();
                    }
                    case QUANTITY -> {
                        return opportunityRepository.minByQuantityCount();
                    }
                }
            }
            case MAX -> {
                switch (value){
                    case EMPLOYEE_COUNT -> {
                        return opportunityRepository.maxByEmployeeCount();
                    }
                    case OPPS -> {
                        return opportunityRepository.maxByAccountCount();
                    }
                    case QUANTITY -> {
                        return opportunityRepository.maxByQuantityCount();
                    }
                }
            }
        }
        return 0;
    }


}
