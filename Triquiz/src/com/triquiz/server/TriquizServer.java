package com.triquiz.server;

import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class TriquizServer implements Runnable{
	private static ServerSocket server;
	private int port;
	
	TriquizServer(int port){
		this.port = port;
	}
	
	@Override
	public void run() {
		try{		

			server = new ServerSocket(port);

			while(true){
				Socket newUser = server.accept();
				Thread newConnection = new Thread(new NewThread(newUser));
				newConnection.start();
			}
		}catch(Exception e){
			JOptionPane.showConfirmDialog(null, "me morí");

			try{
				server.close();
			}catch(Exception ex){
				System.out.println(e.getMessage());
				}
			System.exit(0);
		}
	}

}
