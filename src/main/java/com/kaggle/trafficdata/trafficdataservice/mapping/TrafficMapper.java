package com.kaggle.trafficdata.trafficdataservice.mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.kaggle.trafficdata.trafficdataservice.model.TrafficIncident;
import com.kaggle.trafficdata.trafficdataservice.model.TrafficIncidentREST;

/*
 * A MapStruct mapper used to map the data returned by the DAO
 * to the REST response data
 */
@Mapper
public interface TrafficMapper {

  @Mappings({
      @Mapping(target = "uniqueId", source = "id"),
      @Mapping(target = "date", source = "date"),
      @Mapping(target = "time", source = "time"),
      @Mapping(target = "city", source = "city"),
      @Mapping(target = "location", source = "location"),
      @Mapping(target = "lat", source = "latitude"),
      @Mapping(target = "longitude", source = "longitude"),
      @Mapping(target = "direction", source = "direction"),
      @Mapping(target = "lanes", source = "lanesBlocked"),
      @Mapping(target = "vehiclesInvolved", source = "involved"),
      @Mapping(target = "twitterLink", source = "tweet"),
      @Mapping(target = "source", source = "source"),
  })
  TrafficIncidentREST mapTraffic(TrafficIncident traffic);

  List<TrafficIncidentREST> mapTraffic(List<TrafficIncident> trafficList);
}
