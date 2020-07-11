package com.example.demo.entitys;

import java.io.Serializable;

public class Bet implements Serializable {
private double amount;
private int number;

public Bet( int number ,double amount) {
	this.amount = amount;
	this.number = number;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public int getNumber() {
	return number;
}
public void setNumber(int number) {
	this.number = number;
}
public boolean validateamount() {
	return this.amount<=10000&&this.number>0;
}
public boolean validateNumber() {
	return (this.number<=36 && this.number>=0);

}
}
