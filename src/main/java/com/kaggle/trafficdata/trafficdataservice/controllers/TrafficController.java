package com.kaggle.trafficdata.trafficdataservice.controllers;

import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kaggle.trafficdata.trafficdataservice.services.ITrafficService;

@RestController
@RequestMapping("/")
public class TrafficController {

    private final ITrafficService service;

    public TrafficController (ITrafficService service){
        this.service = service;
    }

    /**
     * Returns the incident data clustered by city
     */
    @GetMapping("/cluster")
    public Response getClusteringByCity() {
        return service.getClusteringByCity();
    }

    /**
     * Returns paginated incident data for a given city
     * Example: <endpoint>/city/{city}}?page=1
     * 
     * @param city the name of the city
     * @param page the page number
     * @return incident data for a specific city
     */
    @GetMapping("/city/{city}")
    public Response getIncidentsByCity(@PathVariable String city, @RequestParam Integer page) {
        return service.getIncidentsByCity(city, page);
    }

    /**
     * Returns a single incident by its unique identifier
     * 
     * @param id the incident ID
     * @return a single incident dataset
     */
    @GetMapping("/city/incidents/{id}")
    public Response getIncidentsById(@PathVariable String id) {
        return service.getIncidentsById(id);
    }
}
