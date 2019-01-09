package com.theone.web.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisDao {

    @Autowired
    private lateinit var stringRedisTemplate: StringRedisTemplate

    fun saveString(key: String, value: String) {
        stringRedisTemplate.opsForValue().set(key, value)
    }

    fun getString(key: String): String? {
        return stringRedisTemplate.opsForValue().get(key)
    }

    fun deleteString(key: String) {
        stringRedisTemplate.delete(key)
    }
}