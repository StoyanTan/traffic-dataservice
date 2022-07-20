package com.kaggle.trafficdata.trafficdataservice.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kaggle.trafficdata.trafficdataservice.dao.TrafficDao;
import com.kaggle.trafficdata.trafficdataservice.mapping.TrafficMapper;
import com.kaggle.trafficdata.trafficdataservice.model.Filter;
import com.kaggle.trafficdata.trafficdataservice.model.ParameterValueObject;
import com.kaggle.trafficdata.trafficdataservice.model.TrafficIncidentREST;

@Service
public class TrafficServiceImpl implements TrafficService {

    private static final String ERROR_MSG = "An error occurred during serialization: %s";

    @Autowired
    private TrafficMapper trafficMapper;

    @Autowired
    private TrafficDao dao;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Response getClusteringByCity() {
        List<ParameterValueObject> result = dao.getClustering(new Filter("city", null));
        ArrayNode arrayNode = objectMapper.createArrayNode();
        objectMapper.writerWithDefaultPrettyPrinter();
        ObjectNode objNode = objectMapper.createObjectNode();
        objNode.putPOJO("children", result);
        arrayNode.add(objNode);
        String jsonResult;
        try {
            jsonResult = objectMapper.writeValueAsString(objNode);
        } catch (JsonProcessingException e) {
            // TODO log error
            return Response.serverError()
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(String.format(ERROR_MSG, e.getMessage()))
                    .build();
        }
        return Response.ok(jsonResult).header("Access-Control-Allow-Origin", "*").build();
    }

    @Override
    public Response getIncidentsByCity(String city, Integer page) {
        Filter filter = new Filter("city", city);
        Integer pageSize = 50;
        Long count = dao.getCount(filter);
        List<TrafficIncidentREST> result = new ArrayList<>();
        if (page == null) {
            result = trafficMapper.mapTraffic(dao.getTraffic(filter, 0, pageSize));
        } else {
            Integer offset = (page - 1) * pageSize;

            result = trafficMapper.mapTraffic(dao.getTraffic(filter, offset, pageSize));
        }
        objectMapper.writerWithDefaultPrettyPrinter();
        ObjectNode objNode = objectMapper.createObjectNode();
        objNode.putPOJO("items", result);
        objNode.put("count", count);
        String jsonResult;
        try {
            jsonResult = objectMapper.writeValueAsString(objNode);
        } catch (JsonProcessingException e) {
            // TODO log error
            return Response.serverError()
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(String.format(ERROR_MSG, e.getMessage()))
                    .build();
        }
        return Response.ok(jsonResult)
                .header("Access-Control-Allow-Origin", "*").build();
    }

    @Override
    public Response getIncidentsById(String id) {
        Filter filter = new Filter("id", id);
        TrafficIncidentREST result = trafficMapper.mapTraffic(dao.getIncidentById(filter));
        objectMapper.writerWithDefaultPrettyPrinter();
        String jsonResult;
        try {
            jsonResult = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            // TODO log error
            return Response.serverError()
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(String.format(ERROR_MSG, e.getMessage()))
                    .build();
        }
        return Response.ok(jsonResult)
                .header("Access-Control-Allow-Origin", "*").build();
    }
}
