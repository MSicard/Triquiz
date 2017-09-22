package com.triquiz.database.beans;

public class Category {
	int idCategory;
	String name;

	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Category(int idCategory, String name) {
		super();
		setIdCategory(idCategory);
		setName(name);
	}
	
	public Category(){
		
	} //nothing
	
	
}
