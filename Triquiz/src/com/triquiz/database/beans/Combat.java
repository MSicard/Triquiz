package com.triquiz.database.beans;

public class Combat {
	int idCombat;
	String name;
	int time;
	
	public int getIdCombat() {
		return idCombat;
	}
	
	public void setIdCombat(int idCombat) {
		this.idCombat = idCombat;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public Combat(int idCombat, String name, int time) {
		super();
		setIdCombat(idCombat);
		setName(name);
		setTime(time);
	}
	
	public Combat(){
		
	}
	
}
