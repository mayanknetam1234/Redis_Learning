package com.Redis.testFirst.controller;

import com.Redis.testFirst.dto.WeatherResponse;
import com.Redis.testFirst.service.interfaces.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/current")
    public WeatherResponse getCurrentWeather(@RequestParam String city) {
        return weatherService.getCurrentWeather(city);
    }
}
