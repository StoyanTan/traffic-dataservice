package com.kaggle.trafficdata.trafficdataservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity model for the traffic incident data
 */
@Entity
@Table(name = "TEST")
public class TrafficIncident {

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "DATE")
	private String date;

	@Column(name = "TIME")
	private String time;

	@Column(name = "CITY")
	private String city;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "LATITUDE")
	private String latitude;

	@Column(name = "LONGITUDE")
	private String longitude;

	@Column(name = "DIRECTION")
	private String direction;

	@Column(name = "LANES")
	private String lanesBlocked;

	@Column(name = "INVOLVED")
	private String involved;

	@Column(name = "TWEET")
	private String tweet;

	@Column(name = "SOURCE")
	private String source;

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

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
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

	public String getLanesBlocked() {
		return lanesBlocked;
	}

	public void setLanesBlocked(String lanesBlocked) {
		this.lanesBlocked = lanesBlocked;
	}

	public String getInvolved() {
		return involved;
	}

	public void setInvolved(String involved) {
		this.involved = involved;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getId() {
		return id;
	}
}
