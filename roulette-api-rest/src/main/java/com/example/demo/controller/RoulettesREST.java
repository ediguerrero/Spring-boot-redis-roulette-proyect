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
import com.example.demo.entitys.Number;
import com.example.demo.entitys.Roulette;
import com.example.demo.repository.RouletteRepository;

@RestController
@RequestMapping("/")
public class RoulettesREST {
	private RouletteRepository rouletterepository;
	
	public RoulettesREST(RouletteRepository rouletterepository) {
		this.rouletterepository = rouletterepository;
	}

	@GetMapping
	public ResponseEntity<Roulette> getRoulette(){
		Roulette x=new Roulette(Roulette.CLOSE,new ArrayList<>());
		x.setStatus(Roulette.OPEN);
		Bet c=new Bet(33,10000);
		x.dobet(c);
		Bet c2=new Bet(33,10000);
		x.dobet(c2);
		return ResponseEntity.ok(x);
		
	}
	@GetMapping("/roulettes")
	public Map<String,Roulette > findAll(){
		return rouletterepository.finAll();
	}

@PostMapping("/closeroulette/{id}")
	public List<Bet> closeroulette(@PathVariable String id){
	Roulette actual= rouletterepository.findbyid(id);
	actual.setStatus(actual.CLOSE);
	rouletterepository.update(actual);
	return actual.getBet();
}

	@PostMapping("/openroulette/{id}")
	public String openroulette(@PathVariable String id) {
Roulette actual= rouletterepository.findbyid(id);
if (Boolean.FALSE.equals(actual.getStatus())){
	actual.setStatus(Roulette.OPEN);
	rouletterepository.update(actual);
	return "opening roulette succesfull";
}
else {
return  "roulette currently open";
}
	}
	@PostMapping("/tobet/{rouletteid}/{number}/{amount}")
	public String toBet(@PathVariable String rouletteid,
						@PathVariable String number,
						@PathVariable String amount) {	
		Roulette actual= rouletterepository.findbyid(rouletteid);
		Bet tmp=new Bet(Integer.parseInt(number),Double.parseDouble(amount));
		boolean betting=actual.dobet(tmp);
		rouletterepository.update(actual);

		return betting?"good luck":"your bet fail";			
	}
	
	@PostMapping("/createroulette")
	public String createroulette() {		
		Roulette roulette=new Roulette(Roulette.CLOSE,new ArrayList<>());
		
		return rouletterepository.save(roulette);
		
	}
	
	@DeleteMapping("/roulettes/{id}")
	public void deleteRoulette(@PathVariable String id) {
		rouletterepository.delete(id);
		
	}	
	
}
