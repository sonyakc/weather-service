package com.weather.domain;

import junit.framework.Assert;

import org.junit.Test;

public class GeoPlaceQueryParametersTest {

	@Test
	public void buildsQuerySuccessfully() {
		GeoPlaceQueryParameters params = new GeoPlaceQueryParameters();
		params.setCountryCode("US");
		params.setZip("10018");
		
		String query = params.buildQuery();
		
		Assert.assertEquals("select * from geo.places where text='10018' and country.code='US'", query);
	}
	
	@Test
	public void defaultCountryCodeIfNotSpecified() {
		GeoPlaceQueryParameters params = new GeoPlaceQueryParameters();
		params.setZip("10018");
		
		String countryCode = params.countryCode();
		
		Assert.assertEquals("US", countryCode);
	}
	
	@Test
	public void doesNotDefaultCountryCodeIfSpecified() {
		GeoPlaceQueryParameters params = new GeoPlaceQueryParameters();
		params.setZip("10018");
		params.setCountryCode("CA");
				
		String countryCode = params.countryCode();
		
		Assert.assertEquals("CA", countryCode);
	}
	
	@Test
	public void defaultFormatIfNotSpecified() {
		GeoPlaceQueryParameters params = new GeoPlaceQueryParameters();
		params.setZip("10018");
		
		String format = params.format();
		Assert.assertEquals("json", format);
	}
	
	@Test
	public void doesNotDefaultFormatIfSpecified() {
		GeoPlaceQueryParameters params = new GeoPlaceQueryParameters();
		params.setZip("10018");
		params.setFormat(QueryFormat.XML.format());
		
		String format = params.format();
		Assert.assertEquals("xml", format);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void buildEmptyQueryParameters() {
		GeoPlaceQueryParameters params = new GeoPlaceQueryParameters();
		
		params.buildQuery();
	}

}
