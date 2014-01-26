package com.weather.domain;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class ForecastTest {

	private static final Gson gson = new Gson();
	
	private String json;
	
	@Before
	public void setup() {
		Forecast forecast = new Forecast();
		forecast.setCode("29");
		forecast.setText("Partly Cloudy");
		forecast.setHigh(BigDecimal.TEN);
		forecast.setLow(BigDecimal.ZERO);
		forecast.setDate("25 Jan 2014");
		forecast.setDay("Sun");
		
		json = gson.toJson(forecast);
	}
	
	@Test
	public void createForecastUsingJson() {
		Forecast fromJson = Forecast.create(json);
		
		Assert.assertEquals("Partly Cloudy", fromJson.getText());
		Assert.assertEquals("25 Jan 2014", fromJson.getDate());
		Assert.assertEquals("29", fromJson.getCode());
	}

	@Test
	public void createFromEmptyJsonString() {
		Forecast fromJson = Forecast.create("");
		
		Assert.assertNull(fromJson);
		Assert.assertNull(fromJson);
		Assert.assertNull(fromJson);
	}

}
