package com.triquiz.database.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.triquiz.database.beans.Combat;


public class CombatControl {
	private Connection conn;
	
	public CombatControl(Connection conn){
		this.conn = conn;
	}
	
	public ArrayList<Combat> getCombat(){
		ArrayList<Combat> combats = new ArrayList<>();
		String sql = "SELECT idCombat, name,time FROM Combat";
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try{
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();
			while(rs.next()){
				Combat combat = new Combat();
				combat.setIdCombat(rs.getInt(1));
				combat.setName(rs.getString(2));
				combat.setTime(rs.getInt(3));
				combats.add(combat);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return combats;
	}
}
