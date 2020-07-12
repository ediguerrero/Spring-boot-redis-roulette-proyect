package com.example.demo.entitys;

import java.io.Serializable;
import java.util.List;

public class Roulette implements Serializable {

	public static final boolean OPEN=true;
	public static final boolean CLOSE=false;
	private String id;
	private boolean status;
	private List<Bet> bets;

	public Roulette(boolean status, List<Bet> bets) {
		this.status = status;
		this.bets = bets;
	}
	
	public List<Bet> getBet() {	return bets; }
	
	public void setBet(List<Bet> bets) { this.bets = bets; }

	public boolean getStatus() { return status; }
	
	public void setStatus(boolean status) {	this.status = status; }

	public String getId() {	return id; }
	
	public void setId(String id) { this.id = id; }
	
	public boolean dobet(Bet tobet) {
		if(Boolean.TRUE.equals((this.getStatus()))&&tobet.validateAmount()&&tobet.validateNumber()) {
			this.getBet().add(tobet);
			
			return true;
		}
		else 
			return false;
	}
}
