package com.kaggle.trafficdata.trafficdataservice.dao;

import java.util.List;

import com.kaggle.trafficdata.trafficdataservice.model.Filter;
import com.kaggle.trafficdata.trafficdataservice.model.ParameterValueObject;
import com.kaggle.trafficdata.trafficdataservice.model.TrafficIncident;

/**
 * A data access component implementing the persistent layer
 */
public interface ITrafficDao {

	/**
	 * Returns a paginated list of incidents according to a given filter criteria
	 * 
	 * @param filter   the filter object
	 * @param offset   the pagination offset
	 * @param pageSize the page size
	 * @return a list of {@link TrafficIncident} data
	 */
	public List<TrafficIncident> getTraffic(Filter filter, int offset, int pageSize);

	/**
	 * Returns the count of the filtered results (utility method used for
	 * pagination)
	 * 
	 * @param filter the filter object
	 * @return the number of filtered results
	 */
	public Long getCount(Filter filter);

	/**
	 * Returns the initial clustering key-value pairs for the overview
	 * 
	 * @param filter the filter object
	 * @return a list of {@link ParameterValueObject} used for the initial overview
	 */
	public List<ParameterValueObject> getClustering(Filter filter);

	/**
	 * Returns detailed data for a specific entry with a link to the
	 * corresponding tweet
	 * 
	 * @param filter the filter object
	 * @return a complete {@link TrafficIncident} result data
	 */
	public TrafficIncident getIncidentById(Filter filter);
}
