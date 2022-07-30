package com.kaggle.trafficdata.trafficdataservice.services;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
public class TrafficService implements ITrafficService {

    private static final String ERROR_MSG = "An error occurred during serialization: %s";

    private final TrafficMapper trafficMapper;

    private final TrafficDao dao;

    private final ObjectMapper objectMapper;

    public TrafficService(TrafficMapper trafficMapper, TrafficDao dao, ObjectMapper objectMapper){
        this.trafficMapper = trafficMapper;
        this.dao = dao;
        this.objectMapper = objectMapper;
    }

    @Override
    public Response getClusteringByCity() {
        List<ParameterValueObject> result = dao.getClustering(new Filter("city", null));
        ArrayNode arrayNode = objectMapper.createArrayNode();
        objectMapper.writerWithDefaultPrettyPrinter();
        ObjectNode objNode = objectMapper.createObjectNode();
        objNode.putPOJO("children", result);
        arrayNode.add(objNode);
        return getResponse(objNode);
    }

    @Override
    public Response getIncidentsByCity(String city, Integer page) {
        Filter filter = new Filter("city", city);
        int pageSize = 50;
        Long count = dao.getCount(filter);
        List<TrafficIncidentREST> result;
        if (page == null) {
            result = trafficMapper.mapTraffic(dao.getTraffic(filter, 0, pageSize));
        } else {
            int offset = (page - 1) * pageSize;
            result = trafficMapper.mapTraffic(dao.getTraffic(filter, offset, pageSize));
        }
        objectMapper.writerWithDefaultPrettyPrinter();
        ObjectNode objNode = objectMapper.createObjectNode();
        objNode.putPOJO("items", result);
        objNode.put("count", count);
        return getResponse(objNode);
    }

    @Override
    public Response getIncidentsById(String id) {
        Filter filter = new Filter("id", id);
        TrafficIncidentREST result = trafficMapper.mapTraffic(dao.getIncidentById(filter));
        objectMapper.writerWithDefaultPrettyPrinter();
        return getResponse(result);
    }

    private Response getResponse(Object objValue) {
        String jsonResult;
        try {
            jsonResult = objectMapper.writeValueAsString(objValue);
        } catch (JsonProcessingException e) {
            // TODO log error
            return Response.serverError()
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(String.format(ERROR_MSG, e.getMessage()))
                    .build();
        }
        return Response.ok(jsonResult).header("Access-Control-Allow-Origin", "*").build();
    }
}
