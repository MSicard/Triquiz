package com.triquiz.socket;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.triquiz.database.beans.*;


public class SocketManager {
	Socket client  = null;
	DataOutputStream outToServer;
    DataInputStream inFromServer;
    
    /**
     * La instancia de cliente será igual a la del socket recibido
     * @param Socket client
     */
    public SocketManager(Socket client) {
        this.client = client;
    }
    
    
    //un usuario entr� 
    public User login(String user, String pass){
        String tag = "[login]:;" + user + ";" + pass + ";";
        try{
        	outToServer = new DataOutputStream(client.getOutputStream()); //Canal de salida
            inFromServer = new DataInputStream(client.getInputStream()); //Canal de entrada
            outToServer.writeUTF(tag); //Enviamos el mensaje con los datos para el login
            String response = "";
            while(response.equals("")) {
            	String[] aux  = inFromServer.readUTF().split(";");
    			response = aux[0];
                switch(response){
                    case "Si":{
                    	//Se pudo hacer el inicio de sesión
                        return new User(Integer.parseInt(aux[1]),aux[2],"");
                    }
                    case "No":{
                    	//No se encontró el usuario
                    	System.out.println("Se encontro null");
                        return null;
                    }
                }
            }
        }catch(Exception e){
        	return null;
        }

		return null;
    }
    
    public User register(String user, String pass){
    	String tag = "[register]:;" + user + ";" + pass + ";";
    	try{
    		outToServer =  new DataOutputStream(client.getOutputStream());
    		inFromServer = new DataInputStream(client.getInputStream());
    		outToServer.writeUTF(tag);
    		String response = "";
    		while(response.equals("")) {
				String[] aux  = inFromServer.readUTF().split(";");
    			response = aux[0];
                switch(response){
                    case "Si":{
                        return new User(Integer.parseInt(aux[1]),aux[2],"");
                    }
                    case "No":{
                        return null;
                    }
                }
            }
    		
    	}catch(Exception e){
    		return null;
    	}
    	return null;
    }
    
    //un usuario sali� 
    public void LogOut() {
            try {
                outToServer = new DataOutputStream(client.getOutputStream());
                inFromServer = new DataInputStream(client.getInputStream());
                outToServer.writeUTF("[logOut]:");
            }catch(Exception ex){
            	System.out.println(ex.getMessage());
            }
        }
    
  //Pide los rankings de un usuario al servidor  
  public ArrayList<Ranking> rank(User user){
	//Crear el tag
	String tag = "[rank]:;" + user.getIdUser() + ";" + user.getNickname() + ";" + "triquizPass" + ";";
	ArrayList<Ranking> rank = new ArrayList<Ranking>();
	//Mandar la petici�n al server
	try{
		outToServer =  new DataOutputStream(client.getOutputStream());
		inFromServer = new DataInputStream(client.getInputStream());
		outToServer.writeUTF(tag);	//Se manda la petici�n 
	//	String respon = inFromServer.readUTF();
		String response = "";
		
		while(response == ""){
			System.out.println("Entro a While rank");
			//Leer la respuesta
			String[] aux  = inFromServer.readUTF().split(";");
			response = aux[0];
			System.out.println(response);
			switch(response){
				case "[rank]:":
					Ranking r;
					for(int i = 1; i < aux.length; i++){
						//Crear un objeto ranking para que se agregue a la lista. 
						r = new Ranking(Integer.parseInt(aux[i]),Integer.parseInt(aux[i + 1]), 
								Integer.parseInt(aux[i + 2]), Integer.parseInt(aux[i + 3]));
						rank.add(r);
						i += 3;
					}
					return rank;
				case "[No]:":
					return null;
			}
		}
	}catch(Exception e){
		JOptionPane.showMessageDialog(null, "Morí al mandar petición");
		System.out.println(e.getStackTrace());
		return null;
	}
	return null;
}
  
  public ArrayList<Ranking> rankWarning(String tag){
	  ArrayList<Ranking> rank = new ArrayList<Ranking>();
	  String[] aux  = tag.split(";");
		switch(aux[0]){
			case "[rank]:":
				Ranking r;
				for(int i = 1; i < aux.length; i++){
					//Crear un objeto ranking para que se agregue a la lista. 
					r = new Ranking(Integer.parseInt(aux[i]),Integer.parseInt(aux[i + 1]), 
							Integer.parseInt(aux[i + 2]), Integer.parseInt(aux[i + 3]));
					rank.add(r);
					i += 3;
				}
				return rank;
			case "[No]:":
				return null;
		}
		return null;
  }
 
  //pide actualizar la lista
  public void updateList(){
	  String tag = "[updateList]:;";
	  try{
			outToServer =  new DataOutputStream(client.getOutputStream());
			outToServer.writeUTF(tag);	//Se manda la petici�n  
	  }catch(Exception e){
		  JOptionPane.showMessageDialog(null, "No pude actualizar la lista");
	  }
  }
  
  
  //mandar un combate (challenge)
  public void sendCombat(String tag){
	  try{
			outToServer =  new DataOutputStream(client.getOutputStream());
//			inFromServer = new DataInputStream(client.getInputStream());
			outToServer.writeUTF(tag);	//Se manda la petici�n  
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }

  //Mandar al rival si aceptamos o no la partida 
  public void answerCombat(String tag){
	  try{
		  System.out.println("Tag AnswerCombat: " + tag);
			outToServer =  new DataOutputStream(client.getOutputStream());
			outToServer.writeUTF(tag);	//Se manda la respuesta
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
  
  
  //Tener la tabla con las preguntas del juego
  public ArrayList<Game> getGameTable(int typeCombat, String rival, User user){
	  String tag;
	 if(typeCombat == 1){
		 tag = "[dare]:;" +  rival + ";" + user.getNickname() + ";";	//Saber que tipo de tabla 
	 }															// se quiere crear
	 else{
		 tag = "[battle]:;" +  rival + ";" + user.getNickname() + ";";	//puede ser battle o dare
	 }
	ArrayList<Game> game = new ArrayList<Game>();
	  try{
		  outToServer = new DataOutputStream(client.getOutputStream());	
		  inFromServer = new DataInputStream(client.getInputStream());
		  outToServer.writeUTF(tag);
		  String response = "";
		  while(response.equals("")){
			String questions = inFromServer.readUTF();
			sendQuestions(questions);
			String[] cmd = questions.split(";");
			Game g;
			for(int i = 3; i < cmd.length; i++){
				//Crear un objeto ranking para que se agregue a la lista. 
				g = new Game(Integer.parseInt(cmd[i]), Integer.parseInt(cmd[i + 1]), 
						cmd[i + 2], cmd[i + 3], cmd[i + 4], cmd[i + 5], cmd[i + 6], cmd[i + 7]); 							
				game.add(g);
				i += 7;
			}
			return game;
	  }
		  
	  }catch(Exception ex){
		  ex.printStackTrace();
	  }
	return null;
  }
  
  private void sendQuestions(String tag){
	  System.out.println("Tag sendQuestions: " + tag);
	  try{
		  outToServer = new DataOutputStream(client.getOutputStream());	
		  outToServer.writeUTF(tag);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
  
  //Mandar que est�s en juego 
  public void inGame(String tag){
	  try{
		  outToServer = new DataOutputStream(client.getOutputStream());
		  outToServer.writeUTF(tag);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
  
  /**
   * tag0 score
   * tag1 points
   * tag2 yo
   * tag3 rival
   * @param tag
   */
  public void score(String tag){
	  try{
		  outToServer = new DataOutputStream(client.getOutputStream());
		  outToServer.writeUTF(tag);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
  
}
