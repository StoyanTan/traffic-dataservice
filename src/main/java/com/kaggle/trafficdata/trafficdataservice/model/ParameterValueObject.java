package com.kaggle.trafficdata.trafficdataservice.model;

/**
 * Entity model for the clustering method
 */
public class ParameterValueObject {

	private String parameter;
	private String count;

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
