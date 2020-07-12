package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entitys.Bet;
import com.example.demo.entitys.Roulette;
import com.example.demo.repository.RouletteRepository;

@RestController
@RequestMapping("/")
public class RoulettesREST {
	private RouletteRepository rouletteRepository;

	public RoulettesREST(RouletteRepository rouletteRepository) {
		this.rouletteRepository = rouletteRepository;
	}

	@GetMapping("/roulettes")
	public Map<String,Roulette > findAll(){	return rouletteRepository.finAll();	}

	@PostMapping("/closeroulette/{id}")
	public List<Bet> closeRoulette(@PathVariable String id){
		Roulette actual= rouletteRepository.findById(id);
		actual.setStatus(actual.CLOSE);
		rouletteRepository.update(actual);
		
		return actual.getBet();
	}

	@PostMapping("/openroulette/{id}")
	public String openRoulette(@PathVariable String id) {
		Roulette actual= rouletteRepository.findById(id);
		if (Boolean.FALSE.equals(actual.getStatus())){
			actual.setStatus(Roulette.OPEN);
			rouletteRepository.update(actual);
			
			return "roulette open successfully";
		}
		else {
			return  "roulette currently open";
		}
	}
	@PostMapping("/tobet/{rouletteid}/{number}/{amount}")
	public String toBet(@PathVariable String rouletteid,
			@PathVariable String number,
			@PathVariable String amount) {	
		Roulette actual= rouletteRepository.findById(rouletteid);
		Bet tmp=new Bet(Integer.parseInt(number),Double.parseDouble(amount));
		boolean betting=actual.dobet(tmp);
		rouletteRepository.update(actual);

		return betting?"good luck":"your bet fail";			
	}

	@PostMapping("/createroulette")
	public String createroulette() {		
		Roulette roulette=new Roulette(Roulette.CLOSE,new ArrayList<>());

		return rouletteRepository.save(roulette);
	}

	@DeleteMapping("/roulettes/{id}")
	public void deleteRoulette(@PathVariable String id) { rouletteRepository.delete(id);	}	

}
