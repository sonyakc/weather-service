package com.weather.domain;

/** 
 * Failed to retrieve weather details
 * @author Sonya
 *
 */
public class WeatherRetrievalException extends RuntimeException {

	private String message;

	public WeatherRetrievalException(String message) {
		this.message = message;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return message;
	}

}
