package com.Redis.testFirst.dto;

import lombok.Data;

@Data
public class WeatherResponse {

    private Location location;
    private Current current;

    @Data
    public static class Location {
        private String name;
        private String region;
        private String country;
    }

    @Data
    public static class Current {
        private double temp_c;
        private double temp_f;
        private Condition condition;
    }

    @Data
    public static class Condition {
        private String text;
        private String icon;
    }
}
