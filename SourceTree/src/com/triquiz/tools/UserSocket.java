package com.triquiz.tools;

import java.net.Socket;

import com.triquiz.database.beans.User;

public class UserSocket {
	
	private Socket client;
	private User user;

	
	public Socket getClient() {
		return client;
	}
	public void setClient(Socket client) {
		this.client = client;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
	public UserSocket(Socket client, User user) {
		super();
		this.client = client;
		this.user = user;
	}
	
	public UserSocket(Socket client, int id, String nickname, String password){
		this.client = client;
		this.user = new User(id, nickname, password);
	}
	
	
	
}
