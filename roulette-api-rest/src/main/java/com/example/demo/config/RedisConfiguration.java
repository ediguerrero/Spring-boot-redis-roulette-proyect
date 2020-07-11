package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.example.demo.entitys.Roulette;


@Configuration
public class RedisConfiguration {

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		
	return new JedisConnectionFactory();		
		
	}
	
	@Bean
	RedisTemplate<String, Roulette> redisTemplate(){
		final RedisTemplate<String, Roulette> redis=new RedisTemplate<>();
		redis.setConnectionFactory(jedisConnectionFactory());
		return redis;
		
	}
}
