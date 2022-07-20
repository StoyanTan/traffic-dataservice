package com.kaggle.trafficdata.trafficdataservice.model;

/**
 * A generic filter structure used in the JPA Criteria query
 */
public class Filter {

	private String filterCriteria;
	private String value;

	public Filter(String filterCriteria, String value) {
		this.filterCriteria = filterCriteria;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFilterCriteria() {
		return filterCriteria;
	}

	public void setFilterCriteria(String filterCriteria) {
		this.filterCriteria = filterCriteria;
	}
}
