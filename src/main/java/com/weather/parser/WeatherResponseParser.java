package com.weather.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.weather.domain.Forecast;

public class WeatherResponseParser implements IResponseParser<List<Forecast>> {

	@Override
	public List<Forecast> parse(String json) {
		if(json == null || json.trim().length() == 0) {
			return new ArrayList<>();
		}
		
		JSONArray array = forecastArray(json);
		return iterate(array);
	}

	private List<Forecast> iterate(JSONArray array) {
		List<Forecast> forecasts = new ArrayList<>();

		Iterator<?> iterator = array.iterator();
		while(iterator.hasNext()) {
			JSONObject next = (JSONObject) iterator.next();
			Forecast forecast = Forecast.create(next.toJSONString());
			forecasts.add(forecast);
		}
		return forecasts;
	}

	private JSONArray forecastArray(String json) {
		Object obj = JSONValue.parse(json);
		JSONObject jsonObj =(JSONObject)obj;
		JSONObject query = (JSONObject) jsonObj.get("query");
		JSONObject results = (JSONObject) query.get("results");
		JSONObject channel = (JSONObject) results.get("channel");
		JSONObject item = (JSONObject) channel.get("item");
		JSONArray array = (JSONArray) item.get("forecast");
		return array;
	}
}
