package com.weather.domain;

/**
 * Represents a weather query to the weather web service
 * 
 * @author Sonya
 *
 */
public class WeatherQueryParameters implements IQueryBuilder {
	private static final String QUERY = "select * from weather.forecast where woeid='WOE_ID'";

	private String woeid;

	public WeatherQueryParameters(String woeid2) {
		this.woeid = woeid2;
	}

	public String getWoeid() {
		return woeid;
	}

	public void setWoeid(String woeid) {
		this.woeid = woeid;
	}

	@Override
	public String buildQuery() {
		if(woeid == null) {
			throw new IllegalArgumentException("woeid must not be null.");
		}
		String weatherQuery = QUERY.replaceAll("WOE_ID", this.woeid);
		return weatherQuery;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((woeid == null) ? 0 : woeid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeatherQueryParameters other = (WeatherQueryParameters) obj;
		if (woeid == null) {
			if (other.woeid != null)
				return false;
		} else if (!woeid.equals(other.woeid))
			return false;
		return true;
	}
	

}
