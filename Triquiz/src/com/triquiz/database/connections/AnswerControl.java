package com.triquiz.database.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.triquiz.database.beans.Answer;
import com.triquiz.database.beans.Question;

public class AnswerControl {
	private Connection conn;
	
	public AnswerControl(Connection conn){
		this.conn = conn;
	}
	
	public ArrayList<Answer> getAnswers(Question question){
		ArrayList<Answer> answers = new ArrayList<>();
		String sql = "SELECT idAnswer, answerText FROM Answer WHERE idQuestion = ? " ;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			statement = conn.prepareStatement(sql);
			statement.setInt(1, question.getIdQuestion());
			rs = statement.executeQuery();
			while(rs.next()){
				Answer ans = new Answer();
				ans.setIdAnswer(rs.getInt(1));
				ans.setAnswerText(rs.getString(2));
				ans.setIdQuestion(question.getIdQuestion());
				answers.add(ans);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			statement = null;
		}
		return answers; 
	}
}
