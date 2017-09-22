package com.triquiz.database.beans;

public class Question {
	int idQuestion;
	String questionText;
	int idAnswer;
	int idCategory;
	
	public int getIdQuestion() {
		return idQuestion;
	}
	
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	
	public String getQuestionText() {
		return questionText;
	}
	
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	public int getIdAnswer() {
		return idAnswer;
	}
	
	public void setIdAnswer(int idAnswer) {
		this.idAnswer = idAnswer;
	}
	
	public int getIdCategory() {
		return idAnswer;
	}
	
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	
	public Question(int idQuestion, String questionText, int idAnswer, int idCategory) {
		super();
		setIdQuestion(idQuestion);
		setQuestionText(questionText);
		setIdAnswer(idAnswer);
		setIdCategory(idCategory);
	}
	
	public Question(){
		
	}
	
	
}
