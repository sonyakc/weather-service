package com.weather.domain;

/** 
 * Represents a geo place query to the weather web service
 * @author Sonya
 *
 */
public class GeoPlaceQueryParameters implements IQueryBuilder {
	private static final String QUERY = "select * from geo.places where text='ZIP_CODE' and country.code='COUNTRY_CODE'";
	private static final String DEFAULT_COUNTRY = "US";
	private static final String DEFAULT_FORMAT = "json";
	
	private String zip;
	private String countryCode;
	private String format;
	public GeoPlaceQueryParameters() {
	}
	public GeoPlaceQueryParameters(String zipcode) {
		this.zip = zipcode;
	}
	public String zip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String countryCode() {
		if(countryCode == null) {
			countryCode = DEFAULT_COUNTRY;
		}
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String format() {
		if(format == null) {
			format = DEFAULT_FORMAT;
		}
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
	/**
	 * Builds the geo place query using the provided zip code and country code
	 */
	@Override
	public String buildQuery() {
		if (zip == null) {
			throw new IllegalArgumentException("zip must not be null.");
		}

		String geoRequestYql = QUERY.replaceAll("ZIP_CODE", this.zip()).replaceAll("COUNTRY_CODE", this.countryCode());
		return geoRequestYql;
	}
}
