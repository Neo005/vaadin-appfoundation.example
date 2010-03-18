package org.vaadin.appfoundation.example.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vaadin.appfoundation.example.data.Advertisement;
import org.vaadin.appfoundation.persistence.facade.FacadeFactory;

/**
 * Controller for the main view
 * 
 * @author Kim
 * 
 */
public class AdsController {

    /**
     * Returns all valid advertisements
     * 
     * @return
     */
    public static List<Advertisement> getAds() {
        // We define a query for fetching all valid advertisements
        String query = "SELECT a FROM Advertisement AS a WHERE a.expires >= :currentdate "
                + "ORDER BY a.date DESC";
        // Define a map with the parameters for the query
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("currentdate", new Date());
        return FacadeFactory.getFacade().list(query, parameters);
    }
}
