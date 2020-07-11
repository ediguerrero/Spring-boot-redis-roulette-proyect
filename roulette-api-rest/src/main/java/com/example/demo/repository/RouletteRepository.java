package com.example.demo.repository;

import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.Roulette;

@Repository
public class RouletteRepository implements RedisRepository{
	private static final String KEY= "Roulette";
	
	private RedisTemplate<String, Roulette> redistemplate;
	private HashOperations hashopetations;
	
	public  RouletteRepository(RedisTemplate<String, Roulette> redistemplate) {
		// TODO Auto-generated constructor stub
		this.redistemplate=redistemplate;
	}
	@PostConstruct
	private void init() {
		hashopetations = redistemplate.opsForHash();
	}
	
	
	@Override
	public Map<String, Roulette> finAll() {
		// TODO Auto-generated method stub
		return hashopetations.entries(KEY);
	}

	@Override
	public Roulette findbyid(String id) {
		// TODO Auto-generated method stub
		return (Roulette) hashopetations.get(KEY, id);
	}

	@Override
	public String save(Roulette roulette) {
		// TODO Auto-generated method stub
		String id=UUID.randomUUID().toString();
		roulette.setId(id);
		hashopetations.put(KEY, id, roulette);
	return id;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
	hashopetations.delete(KEY, id);	
	}
	@Override
	public void update(Roulette roulette) {
		// TODO Auto-generated method stub
		hashopetations.put(KEY, roulette.getId(), roulette);
	}

}
