package com.weather.parser;

public interface IResponseParser<T> {
	T parse(String json);
}
