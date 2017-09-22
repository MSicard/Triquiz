package com.triquiz.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.triquiz.database.beans.Game;
import com.triquiz.gui.Challenge;
import com.triquiz.gui.CombatGUI;
import com.triquiz.gui.Connect;
import com.triquiz.tools.UserSocket;

public class ClientServer implements Runnable{
	private static ServerSocket server;
	private int port;
	private UserSocket me;
	private Connect connect;
//    private DataOutputStream outToServer;
    private DataInputStream inFromServer;
	
	
	public ClientServer(int port, UserSocket me, Connect connect){
		try{
			this.port = port;
			this.me = me;
			this.connect = connect;
//			this.outToServer = new DataOutputStream(me.getClient().getOutputStream());
			this.inFromServer = new DataInputStream(me.getClient().getInputStream());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			server = new ServerSocket(port);
			String fromServer = "";
			while(true){
				
				fromServer = inFromServer.readUTF();
				//Cuando le llega algo del servidor 
				if(!fromServer.equals("")){
					System.out.println("fromServer " + fromServer);
					String aux[] = fromServer.split(";");
					
					switch(aux[0]){
					case "[updatelist]:":
	    				   String[] aux2 = new String[aux.length - 1];
	    				   for(int i = 1; i < aux.length; i++){
	    					   aux2[i - 1] = aux[i];
	    				   }
	    				   connect.updateList(aux2);
	    				   break;
//	    			   case "[logout]:":
//	    				   System.exit(0);
//	    				   break;
	    			   case "[challenge]:":
	    				   Challenge challenge = new Challenge(me, aux, connect);
	    				   challenge.setVisible(true);
	    				   connect.setVisible(false);
	    				   break;    
	    			   case "[available]:":
	    				   break;
	    			   case "[scoreRival]:":
	    				   connect.createWinLose(Integer.parseInt(aux[1]), aux[2]);
	    				   break;
	    			   case "[inGame]:":
	    				   break;
	    			   case "[response]:":
	    				   connect.startGame(aux);
	    				   break;
	    			   case "[questionGame]:":
	    				   questionGame(aux);
	    				   break;
	    			   case "[rank]:":
	    				   break;
	    				   
					}
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
			try{
				server.close();	
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
			System.exit(0);
		}
	}
	
public void questionGame(String[] aux){
	System.out.println("Llego a questionGame");
	ArrayList<Game> game = new ArrayList<Game>();
		Game g;
		System.out.println("Aux en 2" + aux[2]);
		for(int i = 3; i < aux.length; i++){
			//Crear un objeto game para que se agregue a la lista 
			g = new Game(Integer.parseInt(aux[i]), Integer.parseInt(aux[i + 1]), 
					aux[i + 2], aux[i + 3], aux[i + 4], aux[i + 5], aux[i + 6], aux[i + 7]); 							
			game.add(g);
			i += 7;
		}
		System.out.println("Regreso de game: " + game);
		
		connect.startGameRival(aux[2], game);
}

}
