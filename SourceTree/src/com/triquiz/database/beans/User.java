package com.triquiz.database.beans;

public class User {
	int idUser;
	String nickname;
	String password;
	
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User(int idUser, String nickname, String password) {
		super();
		setIdUser(idUser);
		setNickname(nickname);
		setPassword(password);
	}
	
	public User(){
	}
	
	
	
}
