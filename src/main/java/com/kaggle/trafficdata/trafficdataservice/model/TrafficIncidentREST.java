package com.kaggle.trafficdata.trafficdataservice.model;

/**
 * A POJO for the REST response
 */
public class TrafficIncidentREST {

	private Integer uniqueId;
	private String date;
	private String time;
	private String city;
	private String location;
	private String lat;
	private String longitude;
	private String direction;
	private String lanes;
	private String vehiclesInvolved;
	private String twitterLink;
	private String source;

	public Integer getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Integer uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLanes() {
		return lanes;
	}

	public void setLanes(String lanes) {
		this.lanes = lanes;
	}

	public String getVehiclesInvolved() {
		return vehiclesInvolved;
	}

	public void setVehiclesInvolved(String vehiclesInvolved) {
		this.vehiclesInvolved = vehiclesInvolved;
	}

	public String getTwitterLink() {
		return twitterLink;
	}

	public void setTwitterLink(String twitterLink) {
		this.twitterLink = twitterLink;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
