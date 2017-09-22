package com.triquiz.database.connections;

import java.util.ArrayList;

import com.triquiz.database.beans.Answer;
import com.triquiz.database.beans.Game;
import com.triquiz.database.beans.Question;

import com.triquiz.database.connections.AnswerControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GameControl {
	private Connection conn;
	
	public GameControl(Connection conn){
		this.conn = conn;
	}
	
	
	//Esta funci�n va a generar un query que solo traiga 10 preguntas de una sola categor�a
	public ArrayList<Game> dare(){
		//FLOOR(1+ (RAND() * 4))
		ArrayList<Question> questions = new ArrayList<>();
		ArrayList<Answer> answers = new ArrayList<>();
		AnswerControl answerControl = new AnswerControl(this.conn);
		ArrayList<Game> game = new ArrayList<>();
		String sql = "SELECT idQuestion, textQuestion, idAnswer, idCategory FROM Question WHERE idCategory = FLOOR(1+ (RAND() * 4)) LIMIT 10";
//		String sql = "SELECT idCategory, textQuestion, text1, text2, text3, text4, correct FROM Question WHERE idCategory = ? " ;
		//String sql = "SELECT idQuestion, questionText, idAnswer FROM Question WHERE idCategory = ? " ;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Game g;
		try{
			statement = conn.prepareStatement(sql);
			//statement.setInt(1, category.getIdCategory());
			rs = statement.executeQuery();
			while(rs.next()){
				//
				Question q = new Question();
				q.setIdQuestion(rs.getInt(1));
				q.setQuestionText(rs.getString(2));
				q.setIdAnswer(rs.getInt(3));
				q.setIdCategory(rs.getInt(4));
				questions.add(q);
				answers = answerControl.getAnswers(q);
				g = new Game();
				g.setIdCategory(q.getIdCategory());
				g.setTextQuestion(q.getQuestionText());
				g.setText1(answers.get(0).getAnswerText());
				g.setText2(answers.get(1).getAnswerText());
				g.setText3(answers.get(2).getAnswerText());
				g.setText4(answers.get(3).getAnswerText());
				if(answers.get(0).getIdAnswer() == q.getIdAnswer()){
					g.setCorrect(g.getText1());
				}
				else if(answers.get(1).getIdAnswer() == q.getIdAnswer()){
					g.setCorrect(g.getText2());
				}
				else if(answers.get(2).getIdAnswer() == q.getIdAnswer()){
					g.setCorrect(g.getText3());
				}
				else{
					g.setCorrect(g.getText4());
				}
				System.out.println(g.getText1() + " " + g.getText2() + " " + g.getText3() + " " + g.getText4());
				game.add(g);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			statement = null;
		}
		return game; 
	}
	
	//Esta funci�n va a generar un query que solo traiga 30 preguntas de categor�as random
	public ArrayList<Game> battle(){
		ArrayList<Answer> answers = new ArrayList<>();
		QuestionControl questionControl = new QuestionControl(this.conn);
		AnswerControl answerControl = new AnswerControl(this.conn);
		ArrayList<Game> game = new ArrayList<>();
		Question q = null;
		Game g;
		try{
			q = questionControl.getQuestion();
			answers = answerControl.getAnswers(q);
			g = new Game();
			g.setIdCategory(q.getIdCategory());
			g.setTextQuestion(q.getQuestionText());
			g.setText1(answers.get(0).getAnswerText());
			g.setText1(answers.get(1).getAnswerText());
			g.setText1(answers.get(2).getAnswerText());
			g.setText1(answers.get(3).getAnswerText());
			if(answers.get(0).getIdAnswer() == q.getIdAnswer()){
				g.setCorrect(g.getText1());
			}
			else if(answers.get(1).getIdAnswer() == q.getIdAnswer()){
				g.setCorrect(g.getText2());
			}
			else if(answers.get(2).getIdAnswer() == q.getIdAnswer()){
				g.setCorrect(g.getText3());
			}
			else{
				g.setCorrect(g.getText4());
			}
			game.add(g);
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return game; 	
	}
}
