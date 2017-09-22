package com.triquiz.database.beans;

public class Ranking implements Comparable<Ranking>{
	int idRank;
	int idUser;
	int idCategory;
	int score;
	
	public int getIdRank() {
		return idRank;
	}
	
	public void setIdRank(int idRank) {
		this.idRank = idRank;
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public int getIdCategory() {
		return idCategory;
	}
	
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public Ranking(int idRank, int idUser, int idCategory, int score) {
		this.idRank = idRank;
		this.idUser = idUser;
		this.idCategory = idCategory;
		this.score = score;
	}
	
	public Ranking(){
		
	}
	//Esta función es para la creación de Ranking, para que podemos ordenar de una forma fácil
	@Override
	public int compareTo(Ranking o) {
		// TODO Auto-generated method stub
		if(o.getScore() == score){
			return new Integer(idCategory).compareTo(new Integer(o.getIdCategory()));
		}
		else{
			return new Integer(score).compareTo(new Integer(o.getScore()));
		}
	}
	
	
}
