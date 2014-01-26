package com.weather.domain;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.weather.parser.WeatherResponseParser;

public class WeatherJsonParserTest {
	private WeatherResponseParser parser;
	private String json = "{\"query\":{\"count\":1,\"created\":\"2014-01-26T05:20:05Z\",\"lang\":\"en-US\",\"results\":{\"channel\":{\"title\":\"Yahoo! Weather - New York, NY\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/New_York__NY/*http://weather.yahoo.com/forecast/USNJ0589_f.html\",\"description\":\"Yahoo! Weather for New York, NY\",\"language\":\"en-us\",\"lastBuildDate\":\"Sat, 25 Jan 2014 11:50 pm EST\",\"ttl\":\"60\",\"location\":{\"city\":\"New York\",\"country\":\"United States\",\"region\":\"NY\"},\"units\":{\"distance\":\"mi\",\"pressure\":\"in\",\"speed\":\"mph\",\"temperature\":\"F\"},\"wind\":{\"chill\":\"16\",\"direction\":\"0\",\"speed\":\"6\"},\"atmosphere\":{\"humidity\":\"66\",\"pressure\":\"29.48\",\"rising\":\"1\",\"visibility\":\"9\"},\"astronomy\":{\"sunrise\":\"7:09 am\",\"sunset\":\"5:04 pm\"},\"image\":{\"title\":\"Yahoo! Weather\",\"width\":\"142\",\"height\":\"18\",\"link\":\"http://weather.yahoo.com\",\"url\":\"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\"},\"item\":{\"title\":\"Conditions for New York, NY at 11:50 pm EST\",\"lat\":\"40.76\",\"long\":\"-74\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/New_York__NY/*http://weather.yahoo.com/forecast/USNJ0589_f.html\",\"pubDate\":\"Sat, 25 Jan 2014 11:50 pm EST\",\"condition\":{\"code\":\"33\",\"date\":\"Sat, 25 Jan 2014 11:50 pm EST\",\"temp\":\"23\",\"text\":\"Fair\"},\"description\":\"\\n<img src=\\\"http://l.yimg.com/a/i/us/we/52/33.gif\\\"/><br />\\n<b>Current Conditions:</b><br />\\nFair, 23 F<BR />\\n<BR /><b>Forecast:</b><BR />\\nSat - Partly Cloudy. High: 28 Low: 14<br />\\nSun - Mostly Sunny. High: 20 Low: 19<br />\\nMon - Cloudy. High: 35 Low: 12<br />\\nTue - Partly Cloudy. High: 18 Low: 5<br />\\nWed - Mostly Sunny. High: 20 Low: 12<br />\\n<br />\\n<a href=\\\"http://us.rd.yahoo.com/dailynews/rss/weather/New_York__NY/*http://weather.yahoo.com/forecast/USNJ0589_f.html\\\">Full Forecast at Yahoo! Weather</a><BR/><BR/>\\n(provided by <a href=\\\"http://www.weather.com\\\" >The Weather Channel</a>)<br/>\\n\",\"forecast\":[{\"code\":\"29\",\"date\":\"25 Jan 2014\",\"day\":\"Sat\",\"high\":\"28\",\"low\":\"14\",\"text\":\"Partly Cloudy\"},{\"code\":\"34\",\"date\":\"26 Jan 2014\",\"day\":\"Sun\",\"high\":\"20\",\"low\":\"19\",\"text\":\"Mostly Sunny\"},{\"code\":\"26\",\"date\":\"27 Jan 2014\",\"day\":\"Mon\",\"high\":\"35\",\"low\":\"12\",\"text\":\"Cloudy\"},{\"code\":\"30\",\"date\":\"28 Jan 2014\",\"day\":\"Tue\",\"high\":\"18\",\"low\":\"5\",\"text\":\"Partly Cloudy\"},{\"code\":\"34\",\"date\":\"29 Jan 2014\",\"day\":\"Wed\",\"high\":\"20\",\"low\":\"12\",\"text\":\"Mostly Sunny\"}],\"guid\":{\"isPermaLink\":\"false\",\"content\":\"USNJ0589_2014_01_29_7_00_EST\"}}}}}}\r\n";
	
	@Before
	public void setup() {
		parser = new WeatherResponseParser();
	}
	
	@Test
	public void parseValidWeatherJson() {
		List<Forecast> forecastList = parser.parse(json);
		
		Assert.assertEquals(5, forecastList.size());
	}
	
	@Test
	public void parseInvalidWeatherJson() {
		json = "";
		List<Forecast> forecastList = parser.parse(json);
		
		Assert.assertEquals(0, forecastList.size());
	}

}
