package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.example.demo.proxy.MambuConnectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entitys.Bet;
import com.example.demo.entitys.Roulette;
import com.example.demo.repository.RouletteRepository;

@RestController
@RequestMapping("/")
public class RoulettesREST {
	private RouletteRepository rouletteRepository;
	private final MambuConnectionService mambuConnectionService;
	public RoulettesREST(RouletteRepository rouletteRepository, MambuConnectionService mambuConnectionService) {
		this.rouletteRepository = rouletteRepository;
		this.mambuConnectionService = mambuConnectionService;
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




	@GetMapping("/txs")
	public Object getTransactionsByFilter(@RequestParam String startDate, @RequestParam String endDate,
														  @RequestParam(required = false) String type
														 ) throws IOException {
		int i = 0;
		List mambuTrasactions = new ArrayList<>();
		while (i <= mambuTrasactions.size()) {
			Object transactionsData = mambuConnectionService.getMambuTransactionsByFilter(startDate, endDate, type, new Integer(i), 1000,"GLOBAL_COLOMBIA");
			mambuTrasactions.addAll(Arrays.asList(transactionsData));
			i += 1000;
		}
		return mambuTrasactions;
	}

	@GetMapping("/instances")
	public Object getTransactionsByFilter() throws IOException {
		return this.mambuConnectionService.getAllInstances();
	}


}
