package com.Redis.testFirst.service.interfaces;

public interface RedisService {
    <T> T get(String key, Class<T> entityClass);
    void set(String key, Object value);
}
