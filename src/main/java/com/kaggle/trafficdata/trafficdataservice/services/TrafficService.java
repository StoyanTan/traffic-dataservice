package com.kaggle.trafficdata.trafficdataservice.services;

import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * A service for retrieving traffic incident data
 */
public interface TrafficService {

	/**
	 * Returns incidents clustered by city as a JSON array with the structure
	 * expected by d3.js
	 * 
	 * @return a JSON array with the clustered incidents
	 */
	@GetMapping("/")
	public Response getClusteringByCity();

	/**
	 * Returns incidents filtered by city and paginated
	 * 
	 * @param city the name of the city
	 * @param page the page number
	 * @return a JSON array with the filtered incident data
	 */
	@GetMapping("/city/{city}")
	public Response getIncidentsByCity(@PathVariable String city, @RequestParam Integer page);

	/**
	 * Returns a single incident by its unique identifier
	 * 
	 * @param id the incident ID
	 * @return a JSON response containing the incident data
	 */
	@GetMapping("/city/incidents/{id}")
	public Response getIncidentsById(@PathVariable String id);
}
