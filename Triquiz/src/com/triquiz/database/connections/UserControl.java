package com.triquiz.database.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.triquiz.database.beans.User;

public class UserControl {
	private Connection conn;
	
	public UserControl(Connection conn){
		this.conn = conn;
	}
	
	public void createUser(User user){
		
		PreparedStatement statement = null;
		String sql = "INSERT INTO User(nickname, password) VALUES (?,?)";
		try{
			statement = conn.prepareStatement(sql);
			statement.setString(1, user.getNickname());
			statement.setString(2, user.getPassword());
			statement.executeUpdate();
			//Debería cambiar esto :v
			createRanking(login(user.getNickname(),user.getPassword()));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			statement = null;
		}
	}
	
	public ArrayList<User> getUsers(){
		ArrayList<User> users = new ArrayList<>();
		String sql = "SELECT idUser, nickname, password FROM User";
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try{
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();
			while(rs.next()){
				User u = new User();
				u.setIdUser(rs.getInt(1));
				u.setNickname(rs.getString(2));
				u.setPassword(rs.getString(3));
				users.add(u);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return users;
	}
	
	public User login(String nickname, String pass){
		User user = null;
		String sql = "SELECT idUser FROM User WHERE nickname = ? AND password = ?" ;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			statement = conn.prepareStatement(sql);
			statement.setString(1, nickname);
			statement.setString(2, pass);
			rs = statement.executeQuery();
			while(rs.first()){
				user = new User();
				user.setIdUser(rs.getInt(1));
				user.setNickname(nickname);
				user.setPassword(pass);
				break;
			}
		}catch(Exception e){
			System.out.println("Usuario no existe");
			System.out.println(e.getMessage());
		}finally{
			statement = null;
		}
		return user; //User puede ser null, si no hay un usuario con el nombre o contraseña indicado
	}
	
	public User verifyAccount(String nickname){
		User user = null;
		String sql = "SELECT idUser FROM User WHERE nickname = ?" ;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			statement = conn.prepareStatement(sql);
			statement.setString(1, nickname);
			rs = statement.executeQuery();
			while(rs.first()){
				user = new User();
				user.setIdUser(rs.getInt(1));
				user.setNickname(nickname);
				user.setPassword("");
				break;
			}
		}catch(Exception e){
			System.out.println("Usuario no existe");
			System.out.println(e.getMessage());
		}finally{
			statement = null;
		}
		return user; //User puede ser null, si no hay un usuario con el nombre indicado
	}
	
	private void createRanking(User user){
		PreparedStatement statement = null;
		for(int i = 1; i <= 4; i++){
			String sql = "INSERT INTO Ranking(idUser,idCategory,score) VALUES (?,?,?) ";
			try{
				statement = conn.prepareStatement(sql);
				statement.setInt(1,user.getIdUser());
				statement.setInt(2, i);
				statement.setInt(3, 0);
				statement.executeUpdate();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				statement = null;
			}
		}
	}
	
	
}
