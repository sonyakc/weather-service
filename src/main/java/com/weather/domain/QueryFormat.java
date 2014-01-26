package com.weather.domain;

import javax.ws.rs.core.MediaType;

/** 
 * Represents the query format
 * 
 * @author Sonya
 *
 */
public enum QueryFormat {
	XML("xml", MediaType.APPLICATION_XML), JSON("json", MediaType.APPLICATION_JSON);

	private String format;
	private String mediaType;
	
	/**
	 * Get the simple string format
	 * @return
	 */
	public String format() {
		return format;
	}
	
	/**
	 * Gets the media type representation
	 * @return
	 */
	public String mediaType() {
		return mediaType;
	}

	private QueryFormat(String shortFormat, String mediaType) {
		this.format = shortFormat;
		this.mediaType = mediaType;
	}

	/**
	 * Finds the corresponding enum format type
	 * @param providedFormat
	 * @return
	 */
	public static QueryFormat findFormat(String providedFormat) {
		switch(providedFormat) {
			case "json":
				return QueryFormat.JSON;
			case "xml":
				return QueryFormat.XML;
			default:
				return QueryFormat.JSON;
		}
	}
}
