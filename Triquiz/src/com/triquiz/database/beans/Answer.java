package com.triquiz.database.beans;

public class Answer {
	int idAnswer;
	int idQuestion;
	String answerText;
	
	public int getIdAnswer() {
		return idAnswer;
	}
	
	public void setIdAnswer(int idAnswer) {
		this.idAnswer = idAnswer;
	}
	
	public int getIdQuestion() {
		return idQuestion;
	}
	
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	
	public String getAnswerText() {
		return answerText;
	}
	
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	
	public Answer(int idAnswer, int idQuestion, String answerText) {
		super();
		setIdAnswer(idAnswer);
		setIdQuestion(idQuestion);
		setAnswerText(answerText);
	}
	
	public Answer(){
		
	}
	
}
