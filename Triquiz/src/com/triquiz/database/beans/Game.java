package com.triquiz.database.beans;

public class Game {

	int idGame;
	int idCategory;
	String textQuestion;
	String text1;
	String text2;
	String text3;
	String text4;
	String correct;
	public int getIdGame() {
		return idGame;
	}
	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public String getTextQuestion() {
		return textQuestion;
	}
	public void setTextQuestion(String textQuestion) {
		this.textQuestion = textQuestion;
	}
	public String getText1() {
		return text1;
	}
	public void setText1(String text1) {
		this.text1 = text1;
	}
	public String getText2() {
		return text2;
	}
	public void setText2(String text2) {
		this.text2 = text2;
	}
	public String getText3() {
		return text3;
	}
	public void setText3(String text3) {
		this.text3 = text3;
	}
	public String getText4() {
		return text4;
	}
	public void setText4(String text4) {
		this.text4 = text4;
	}
	public String getCorrect() {
		return correct;
	}
	public void setCorrect(String correct) {
		this.correct = correct;
	}
	public Game(int idGame, int idCategory, String textQuestion, String text1, String text2, String text3, String text4,
			String correct) {
		super();
		this.idGame = idGame;
		this.idCategory = idCategory;
		this.textQuestion = textQuestion;
		this.text1 = text1;
		this.text2 = text2;
		this.text3 = text3;
		this.text4 = text4;
		this.correct = correct;
	}
	
	public Game(){
		
	}
	
}
