package com.weather.client;

import org.junit.Before;
import org.junit.Test;

import com.weather.domain.WeatherRetrievalException;


/**
 * 
 * @author Sonya
 *
 */
public class BetterWeatherServiceClientTest {

	@Before
	public void setup() {
	}
	
	@Test
	public void successfulWeatherRetrievalJson() {
		String[] args = {"10018", "json"};
		BetterWeatherServiceClient.main(args);
	}
	
	@Test(expected = WeatherRetrievalException.class)
	public void invalidNumberofArguments() {
		String[] args = {"10018", "json", "abcde"};
		BetterWeatherServiceClient.main(args);
	}
	
	@Test(expected = WeatherRetrievalException.class)
	public void invalidZipCode() {
		String[] args = {"abcdef", "json"};
		BetterWeatherServiceClient.main(args);
	}
	
	@Test(expected = WeatherRetrievalException.class)
	public void invalidArgs() {
		BetterWeatherServiceClient.main(null);
	}
}
