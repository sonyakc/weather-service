package com.weather.client;

import com.google.inject.AbstractModule;

public class WeatherModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IWeatherDao.class).to(WeatherDao.class);
	}

}
