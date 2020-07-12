package com.example.demo.repository;

import java.util.Map;

import com.example.demo.entitys.Roulette;

public interface RedisRepository 
{
	Map<String, Roulette> finAll();

	Roulette findById(String id) ;

	String save(Roulette roulette);

	void delete(String id);

	void update(Roulette roulette);
}
