package com.Redis.testFirst.service.interfaces;

import com.Redis.testFirst.dto.WeatherResponse;

public interface WeatherService {
    public WeatherResponse getCurrentWeather(String city);
}
