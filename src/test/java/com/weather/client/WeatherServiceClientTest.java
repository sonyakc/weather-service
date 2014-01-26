package com.weather.client;
import org.junit.Before;
import org.junit.Test;

import com.weather.client.WeatherServiceClient;
import com.weather.domain.WeatherRetrievalException;


/**
 * 
 * @author Sonya
 *
 */
public class WeatherServiceClientTest {

	@Before
	public void setup() {
	}
	
	@Test
	public void successfulWeatherRetrievalJson() {
		String[] args = {"10018", "json"};
		WeatherServiceClient.main(args);
	}
	
	@Test(expected = WeatherRetrievalException.class)
	public void invalidNumberofArguments() {
		String[] args = {"10018", "json", "abcde"};
		WeatherServiceClient.main(args);
	}
	
	@Test(expected = WeatherRetrievalException.class)
	public void invalidZipCode() {
		String[] args = {"abcdef", "json"};
		WeatherServiceClient.main(args);
	}
}
