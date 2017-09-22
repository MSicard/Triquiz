package com.triquiz.database.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import com.triquiz.database.beans.Ranking;
import com.triquiz.database.beans.User;

public class RankingControl {
	private Connection conn;
	
	public RankingControl(Connection conn){
		this.conn = conn;
	}
	/**
	 * 
	 * @param ranking
	 */
	public void createRanking(Ranking ranking){
		
		PreparedStatement statement = null;
		String sql = "INSERT INTO Ranking(idUser, idCategory, score) VALUES (?,?,?)";
		try{
			statement = conn.prepareStatement(sql);
			statement.setInt(1, ranking.getIdUser());
			statement.setInt(2, ranking.getIdCategory());
			statement.setInt(3, ranking.getScore());
			statement.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			statement = null;
		}
	}
	/**
	 * 
	 * @param ranking
	 */
	
	public void updateRanking(Ranking ranking){
		PreparedStatement statement = null;
		String sql = "UPDATE Ranking SET score = ? WHERE idUser = ? AND idCategory = ?";
		try{
			statement = conn.prepareStatement(sql);
			statement.setInt(1, ranking.getScore());
			statement.setInt(2, ranking.getIdUser());
			statement.setInt(3, ranking.getIdCategory());	
			statement.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			statement = null;
		}
	}
	
	/**
	 * Regresa el ranking de las categorías de un usuario
	 * @return
	 */
	public ArrayList<Ranking> getRanking(User u){
		ArrayList<Ranking> ranking = new ArrayList<>();
		String sql = "SELECT idRank, idCategory, score FROM Ranking WHERE idUser = ?";
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			statement = conn.prepareStatement(sql);
			statement.setInt(1, u.getIdUser());
			rs = statement.executeQuery();
			while(rs.next()){
				Ranking r = new Ranking();
				r.setIdRank(rs.getInt(1));
				r.setIdCategory(rs.getInt(2));
				r.setScore(rs.getInt(3));
				r.setIdUser(u.getIdUser());
				ranking.add(r);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			statement = null;
			//DBConnection.close();
		}
		return ranking;
	}
}
