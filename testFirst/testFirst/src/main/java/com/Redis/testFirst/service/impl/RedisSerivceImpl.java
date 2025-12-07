package com.Redis.testFirst.service.impl;

import com.Redis.testFirst.service.interfaces.RedisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisSerivceImpl implements RedisService {
    private static final Logger log = LoggerFactory.getLogger(RedisSerivceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public <T> T get(String key, Class<T> entityClass) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            if (o == null) return null;
            if (entityClass.isInstance(o)) {
                return entityClass.cast(o);
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(), entityClass);
        } catch (Exception e) {
            log.error("Exception ", e);
            return null;
        }
    }

    @Override
    public void set(String key, Object value) {
        try {
            Object toStore;
            if (value instanceof String) {
                toStore = value;
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                toStore = objectMapper.writeValueAsString(value);
            }
            redisTemplate.opsForValue().set(key, toStore);
        } catch (Exception e) {
            log.error("Exception ", e);
        }
    }
}
