package com.weather.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * query
 *  -- diagnostics
 *  -- results
 *     -- place
 *       -- woeid
 * @author Sonya
 *
 */
@XmlRootElement 
public class GeoPlaceResponse {
	private Query query;

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	/**
	 * Retrieves woeid from the response
	 * 
	 * @param json
	 * @return
	 */
	public static String retrieveWoeid(GeoPlaceResponse json) {
		Query query = json.getQuery();
		if(query.getResults() != null) {
			return query.getResults().getPlace().getWoeid();
		} else {
			throw new WeatherRetrievalException("no valid results for geo place response");
		}
	}

}
