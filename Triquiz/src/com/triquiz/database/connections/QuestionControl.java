package com.triquiz.database.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.triquiz.database.beans.Category;
import com.triquiz.database.beans.Question;

public class QuestionControl {
	private Connection conn;
	
	public QuestionControl(Connection conn){
		this.conn = conn;
	}
	
	public ArrayList<Question> getQuestions(Category category){
		ArrayList<Question> questions = new ArrayList<>();
		String sql = "SELECT idQuestion, questionText, idAnswer FROM Question WHERE idCategory = ? " ;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			statement = conn.prepareStatement(sql);
			statement.setInt(1, category.getIdCategory());
			rs = statement.executeQuery();
			while(rs.next()){
				Question q = new Question();
				q.setIdQuestion(rs.getInt(1));
				q.setQuestionText(rs.getString(2));
				q.setIdAnswer(rs.getInt(3));
				q.setIdCategory(category.getIdCategory());
				questions.add(q);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			statement = null;
		}
		return questions; 
	}
	
	public Question getQuestion(){
		Question q = null;
		String sql = "SELECT idQuestion, questionText, idAnswer, idCategory FROM Question WHERE idCategory = FLOOR(1+ (RAND() * 4)) LIMIT 10";
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();
			if(rs.first()){
				q = new Question();
				q.setIdQuestion(rs.getInt(1));
				q.setQuestionText(rs.getString(2));
				q.setIdAnswer(rs.getInt(3));
				q.setIdCategory(rs.getInt(4));
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			statement = null;
		}
		return q;
	}
}
