package com.weather.client;

import java.util.List;

import com.weather.domain.Forecast;

/**
 * Start of a data access, persistence layer
 * @author Sonya
 *
 */
public interface IWeatherDao {
	Forecast create(Forecast forecast);
	
	List<Forecast> queryAll();
}
