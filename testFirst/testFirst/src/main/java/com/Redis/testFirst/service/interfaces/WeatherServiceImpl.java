package com.Redis.testFirst.service.interfaces;

import com.Redis.testFirst.dto.WeatherResponse;
import com.Redis.testFirst.service.impl.WeatherService;
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

    public WeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public WeatherResponse getCurrentWeather(String city) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/current.json")
                .queryParam("key", apiKey)
                .queryParam("q", city)
                .toUriString();

        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}
