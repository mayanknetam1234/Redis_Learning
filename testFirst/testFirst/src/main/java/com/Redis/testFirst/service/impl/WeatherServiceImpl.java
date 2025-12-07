package com.Redis.testFirst.service.impl;

import com.Redis.testFirst.dto.WeatherResponse;
import com.Redis.testFirst.service.interfaces.WeatherService;
import com.Redis.testFirst.service.interfaces.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Value("${weatherapi.key}")
    private String apiKey;

    @Value("${weatherapi.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;
    private final RedisService redisService;

    public WeatherServiceImpl(RestTemplate restTemplate, RedisService redisService) {
        this.restTemplate = restTemplate;
        this.redisService = redisService;
    }
    @Override
    public WeatherResponse getCurrentWeather(String city) {
        WeatherResponse cached = redisService.get("weather_of_" + city, WeatherResponse.class);
        if (cached != null) {
            return cached;
        }
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/current.json")
                .queryParam("key", apiKey)
                .queryParam("q", city)
                .toUriString();

        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
        if (response != null) {
            redisService.set("weather_of_" + city, response);
        }
        return response;
    }
}
