package com.weather.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.weather.domain.Forecast;
import com.weather.domain.GeoPlaceQueryParameters;
import com.weather.domain.QueryFormat;
import com.weather.domain.WeatherQueryParameters;
import com.weather.domain.WeatherRetrievalException;
import com.weather.parser.WeatherResponseParser;

/**
 * Client to get weather data
 * 
 * @author Sonya
 *
 */
public class BetterWeatherServiceClient {

	private static Logger logger = LoggerFactory.getLogger(BetterWeatherServiceClient.class);   

	private static final String BASE_URI = "http://query.yahooapis.com/v1/public/yql";

	private IWeatherDao dao;
	
	@Inject
	public BetterWeatherServiceClient(IWeatherDao dao) {
		super();
		this.dao = dao;
	}

	public BetterWeatherServiceClient() { }	
	
	/**
	 * My program accepts 2 args, the first being the zipcode and second being
	 * the response representation e.g. json
	 * @param args
	 */
	public static void main(String[] args) {
		if(args == null || args.length != 2) {
			throw new WeatherRetrievalException("need exactly two arguments to the program");
		}
		String zipcode = args[0];
		String format = args[1]; // json or xml
		
		new BetterWeatherServiceClient().search(zipcode, format);
	}

	private void search(String zipcode, String format) {
		Client client = ClientBuilder.newClient();

		String query = new GeoPlaceQueryParameters(zipcode).buildQuery();
		String weatherQuery = new WeatherQueryParameters(query).buildQuery();
		String improvedQuery = weatherQuery.replaceAll("WOE_ID", query);
		
		WebTarget target = client.target(BASE_URI)
				.queryParam("q", improvedQuery)
				.queryParam("crossProduct", "optimized");
		
		String mediaType = QueryFormat.findFormat(format).mediaType();
		Response response = target
				.request(mediaType)
				.get();

		int status = response.getStatus();
		String readEntity = response.readEntity(String.class);
		if(200 == status && readEntity != null) {
			WeatherResponseParser parser = new WeatherResponseParser();
			logger.info(readEntity);
			List<Forecast> forecastList = parser.parse(readEntity);
			for(Forecast f : forecastList) {
				logger.info(f.toString());
			}
		} else {
			throw new WeatherRetrievalException("failed to retrieve weather forecast details for zipcode="+zipcode);
		}	
		
	}

	@SuppressWarnings("unused")
	private void createForecast(Forecast f) {
		Injector createInjector = Guice.createInjector(new WeatherModule());
		dao = createInjector.getInstance(IWeatherDao.class);
		dao.create(f);
	}

}
