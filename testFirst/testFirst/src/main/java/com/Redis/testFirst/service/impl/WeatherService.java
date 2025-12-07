package com.Redis.testFirst.service.impl;

import com.Redis.testFirst.dto.WeatherResponse;

public interface WeatherService {
    public WeatherResponse getCurrentWeather(String city);
}
