package com.example.demo.config;

import com.example.demo.proxy.MambuConnectionApi;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.example.demo.entitys.Roulette;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.Objects;


@Configuration
@Slf4j
public class RedisConfiguration {
   private Retrofit retrofitApiGatewayMambu;
	@Bean
	JedisConnectionFactory jedisConnectionFactory() { return new JedisConnectionFactory(); }

	@Bean
	RedisTemplate<String, Roulette> redisTemplate(){
		final RedisTemplate<String, Roulette> redis=new RedisTemplate<>();
		redis.setConnectionFactory(jedisConnectionFactory());

		return redis;
	}

	private void apiGatewayMambuRetroFitConfiguration() {
		this.retrofitApiGatewayMambu = this.retroFitConfiguration("https://amp-processor-api.global66.com/ci" + "/mambu");
	}
	private Retrofit retroFitConfiguration(String url) {
		//log.info("Cargando configuracion para conexion a lambda ");

		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

		return new Retrofit.Builder()
				.baseUrl(url + "/")
				.client(client)
				.addConverterFactory(JacksonConverterFactory.create(getObjectMapper()))
				.build();
	}

	private ObjectMapper getObjectMapper() {
		return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
				.setDateFormat(new StdDateFormat())
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
				.enable(JsonGenerator.Feature.IGNORE_UNKNOWN)
				.enable(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS);
	}

	@Bean
	MambuConnectionApi mambuApi() {
		if (Objects.isNull(retrofitApiGatewayMambu)) {
			this.apiGatewayMambuRetroFitConfiguration();
		}
		return retrofitApiGatewayMambu.create(MambuConnectionApi.class);
	}
}
