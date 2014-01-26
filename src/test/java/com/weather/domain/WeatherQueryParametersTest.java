package com.weather.domain;

import junit.framework.Assert;

import org.junit.Test;

public class WeatherQueryParametersTest {

	@Test
	public void buildQuerySuccessfully() {
		WeatherQueryParameters params = new WeatherQueryParameters("215508");
		
		String buildQuery = params.buildQuery();
		
		Assert.assertEquals("select * from weather.forecast where woeid='215508'", buildQuery);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void buildEmptyQueryParameters() {
		WeatherQueryParameters params = new WeatherQueryParameters(null);
		
		params.buildQuery();
	}

}
