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
import com.weather.domain.GeoPlaceResponse;
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
public class WeatherServiceClient {

	private static Logger logger = LoggerFactory.getLogger(WeatherServiceClient.class);   

	private static final String BASE_URI = "http://query.yahooapis.com/v1/public/yql";

	private IWeatherDao dao;
	
	@Inject
	public WeatherServiceClient(IWeatherDao dao) {
		super();
		this.dao = dao;
	}

	public WeatherServiceClient() { }	
	
	/**
	 * My program accepts 2 args, the first being the zipcode and second being
	 * the response representation e.g. json
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length != 2) {
			throw new WeatherRetrievalException("need exactly two arguments to the program");
		}
		String zipcode = args[0];
		String format = args[1]; // json or xml
		
		new WeatherServiceClient().search(zipcode, format);
	}

	private void search(String zipcode, String format) {
		Client client = ClientBuilder.newClient();
		//e.g. "10018"
		String query = new GeoPlaceQueryParameters(zipcode).buildQuery();
		
		WebTarget target = client.target(BASE_URI)
				.queryParam("q", query)
				.queryParam("crossProduct", "optimized");
		
		String mediaType = QueryFormat.findFormat(format).mediaType();
		Response response = target
				.request(mediaType)
				.get();

		int status = response.getStatus();
		if(200 == status) {
			GeoPlaceResponse readEntity = response.readEntity(GeoPlaceResponse.class);
			getWeatherForecast(readEntity, client, mediaType);
		} else {
			throw new WeatherRetrievalException("failed to retrieve weather details for zip =" +zipcode);
		}
		
	}

	private void getWeatherForecast(GeoPlaceResponse json, Client client, String mediaType) {
		if(json == null) {
			throw new WeatherRetrievalException("failed to successfully retrieve geo place details");
		}
		String woeid = GeoPlaceResponse.retrieveWoeid(json);
		
		String weatherQuery = new WeatherQueryParameters(woeid).buildQuery();
		Response weatherResponse = client.target(BASE_URI)
				.queryParam("q", weatherQuery)
				.queryParam("crossProduct", "optimized")
				.request(mediaType).get();
		
		int status = weatherResponse.getStatus();
		if(200 == status) {
			WeatherResponseParser parser = new WeatherResponseParser();
			String readEntity = weatherResponse.readEntity(String.class);
			System.out.println(readEntity);
			List<Forecast> forecastList = parser.parse(readEntity);
			for(Forecast f : forecastList) {
//				System.out.println(f.toString());
				logger.info(f.toString());
//				createForecast(f);
			}
		} else {
			throw new WeatherRetrievalException("failed to retrieve weather details for woeid =" +woeid);
		}
	}

	@SuppressWarnings("unused")
	private void createForecast(Forecast f) {
		Injector createInjector = Guice.createInjector(new WeatherModule());
		dao = createInjector.getInstance(IWeatherDao.class);
		dao.create(f);
	}

}
