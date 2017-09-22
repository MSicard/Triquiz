package com.triquiz.server;
import com.triquiz.database.beans.*;
import com.triquiz.database.connections.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class NewThread implements Runnable {
	public static ArrayList<Socket> sockets = new ArrayList<>();
	public static ArrayList<User> players = new ArrayList<>();
	final static int PORT = 4444;
	DataInputStream input;
	DataOutputStream output;
	Socket socket;
	User user;
	Connection conn;
	UserControl userControl;
	
	NewThread(Socket socket) throws Exception {
		//Crea el canal de entrada y salida con el input y output, y una conexi�n a la base de datos
		try{
			this.socket = socket;
			this.input = new DataInputStream(socket.getInputStream());
			this.output = new DataOutputStream(socket.getOutputStream());
			this.conn = DBConnection.getInstance();
			this.userControl = new UserControl(this.conn);
		}catch(Exception e){
			System.out.println(e.getMessage());
			try{
				socket.close();
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null,ex.getMessage());
			}
		}
	}
	
	@Override
	public void run() {
		try{
			while(true){
				//Separar lo que se lee
				String tag = input.readUTF();
				String cmd[] = tag.split(";");
				System.out.println(cmd[0]);
				//Casos 
				switch(cmd[0]){
					case "[login]:":
						//Se crea un objeto usuario
						user = userControl.login(cmd[1], cmd[2]);
						FoundUser(user);
						break;
					case "[register]:":
						if(userControl.verifyAccount(cmd[1]) == null){
							userControl.createUser(new User(0, cmd[1], cmd[2]));
							user = userControl.login(cmd[1], cmd[2]);
							FoundUser(user);
						}
						else{
							output.writeUTF("No;");
						}
						break;
					case "[updatelist]:":
						updateList();
						break;
					case "[rank]:":
						rank(Integer.parseInt(cmd[1]), cmd[2], cmd[3]);
						break;
					case "[challenge]:":
						sendChallenge(tag, cmd[2]); //mandar el tag y nombre del rival
						break;
					case "[response]:":
						response(tag, cmd);
						break;
					//aqui se necesita hacer una funci�n que consiga 10 preguntas de una categor�a al azar
					case "[dare]:":	
						dare(cmd);
						break;
					//batalla, escoge 30 preguntas de todas las que se tiene
					case "[battle]:":
						dare(cmd);
						break;
					case "[score]:":
						sendScore(cmd);
						break;
					case "[questionGame]:":
						sendQuestion(tag);
						break;
					case "[logOut]:":
						logOut();
						break;
						
				}
			}
		}catch(IOException e){
			try{
				socket.close();
			}catch(Exception ex){
				
			}
		}
	}
	
	public void FoundUser(User user) throws IOException{
		if(user != null){
			//Si existe, lo agregamos en la lista
			sockets.add(this.socket);
			players.add(user);
			output.writeUTF("Si;" + user.getIdUser() + ";" + user.getNickname());
			updateList();
		}else{
			//EnviarRespuesta de que no salió chido
			output.writeUTF("No;");
			System.out.println("Error, usuario no encontrado");
			}
	}
	
	//método para actualizar la lista
	private void updateList(){
	    int i = 0;
    	//crear una lista y ponerle los usuarios
        String update = "";
	    try {
	    	update = "[updatelist]:;";
	        for(i = 0; i < players.size(); i++){
	            update += players.get(i).getNickname() + ";";
	        }
	        //mandar la lista
	        for (i = 0; i < sockets.size(); i++) {	//manda el update
	            new DataOutputStream(sockets.get(i).getOutputStream()).writeUTF(update);
	        }
	        
	    } catch (Exception ex) {
	        players.remove(i);
	        try {
	            sockets.get(i).close();
	        } catch (IOException ex1) {
	        	System.out.println(ex.getMessage());
	        }
	        sockets.remove(i);
	        //si no funciona, vuelve a intentarlo
	        updateList();
	    }
	}
	
	public void logOut(){
		try{
			output.writeUTF("[logout]:");
             players.remove(user);
             socket.close();
             sockets.remove(socket);
             updateList();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	//Funci�n de peticiones
	public void rank(int id, String nickname, String pass){
		String update;
		try{
			//Crear un user para mandar al getRanking
			User auxUser = new User(id, nickname, pass);	
			//Conseguir de RankingControl el arraylist 
			ArrayList<Ranking> rank = new RankingControl(this.conn).getRanking(auxUser);
			update = "";
			if(!rank.isEmpty()){
				update = "[rank]:;";
				//concatenar los valores que te da para regresarlos
				for( Ranking r : rank){
					update += String.valueOf(r.getIdRank()) + ";" +
							String.valueOf(r.getIdUser()) + ";" +
							String.valueOf(r.getIdCategory()) + ";" +
							String.valueOf(r.getScore()) + ";";
				}
			}
			else{
				update = "[No];";	
			}
			System.out.println("Escribiendo update: " + update);
			output.writeUTF(update);	//regresa el String				
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("rank: " + e.getMessage());
		}
	}
	
	public void sendChallenge(String tag, String rival){
		try{
			boolean flag = false;
			for(int i = 0; i < players.size(); i++){				//busca en lista players
				if(players.get(i).getNickname().equals(rival)){			//consigue el socket que coincide con el nombre
					new DataOutputStream(sockets.get(i).getOutputStream()).writeUTF(tag);	//manda mensaje
					flag = true;	//bandera para saber si lo encontr�
				}
			}
			if(!flag){		//si no lo encuentra manda error
				output.writeUTF("[Error]:;Usuario no encontrado");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}
	/**
	 * Cmd[3] = Jugador
	 * Cmd[2] = Rival
	 * cmd[1] = respuesta (Si, no)
	 * cmd[0] = etiqueta (response)
	 * @param tag
	 * @param cmd
	 */
	//manda la respuesta del rival al usuario que mand� el reto
	public void response(String tag, String[] cmd){
		try{
			for(int i = 0; i < players.size(); i++){	//Busca el jugador
				if(players.get(i).getNickname().equals(cmd[3])){
					new DataOutputStream(sockets.get(i).getOutputStream()).writeUTF(tag); //Manda la respuesta
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}
	
	/**
	 * cmd[0]: [dare]
	 * cmd[1]: NickName del rival
	 * Aqui se necesita pedir que se haga la tabla con las siguientes caracteristica:
	 * Son 10 preguntas
	 * Todas son de la misma categor�a
	 * @param cmd
	 */
	public void dare(String[] cmd){
		String tag;
		try{
			//Se crea un arraylist con las preguntas, y se le pide a gamecontrol que haga la tabla
			ArrayList<Game> game = new GameControl(this.conn).dare();
			tag = "";
			//Se revisa si se crea bien el query
			if(!game.isEmpty()){
				//se hace un nuevo tag
				tag = "[questionGame]:;" + cmd[1] + ";" + cmd[2] + ";";
				System.out.println("questionGame: " + cmd[1] + cmd[2]);
				//Se va concatenando para mandar en el tag
				for(Game g: game){
					tag += String.valueOf(g.getIdGame()) + ";" +
				String.valueOf(g.getIdCategory()) + ";" +
				g.getTextQuestion() + ";" + g.getText1() + ";" +
				g.getText2() + ";" + g.getText3() + ";" +
				g.getText4() + ";" + g.getCorrect() + ";";
				}
			}else{
				//Si est� vac�a se manda que hubo un error
				tag = "[Error]:";
			}
			System.out.println(tag);
			output.writeUTF(tag);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("dare " + e.getMessage() );
		}
	}
	
	void sendQuestion(String tag){
		String cmd[] = tag.split(";");
		try{
			System.out.println("Llego a mandar preguntas al enemigo");
			for(int i = 0; i < players.size(); i++){	//Busca el jugador
				if(players.get(i).getNickname().equals(cmd[1])){
					new DataOutputStream(sockets.get(i).getOutputStream()).writeUTF(tag); //Manda la respuesta
				}
			}
		}catch(Exception s){
			s.printStackTrace();
		}
	}
	
	/**
	 * cmd[0]: [battle]
	 * cmd[1]: NickName del usuario a quien se le est� creando la partida
	 * Aqui se necesita pedir que se haga la tabla con las siguientes caracteristicas:
	 * Son 30 preguntas
	 * Son categor�as variadas
	 * @param cmd
	 */
	public void battle(String[] cmd){
		String tag;
		try{
			//Se crea un arraylist con las preguntas, y se le pide a gamecontrol que haga la tabla
			//Se necesita dos tipos de funciones
			//Una para la tabla de battle y otra para la tabla de dare
			ArrayList<Game> game = new GameControl(this.conn).battle();
			tag = "";
			//Se revisa si se crea bien el query
			if(!game.isEmpty()){
				//se hace un nuevo tag
				tag = "[battle]:;";
				//Se va concatenando para mandar en el tag
				for(Game g: game){
					tag += String.valueOf(g.getIdGame()) + ";" +
				String.valueOf(g.getIdCategory()) + ";" +
				g.getTextQuestion() + ";" + g.getText1() + ";" +
				g.getText2() + ";" + g.getText3() + ";" +
				g.getText4() + ";" + g.getCorrect() + ";";
				}
			}else{
				//Si est� vac�a se manda que hubo un error
				tag = "[Error]:";
			}
			//Se manda el tag
			output.writeUTF(tag);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("dare " + e.getMessage() );
		}
	}
	/**
	 * cmd[0] = [score]:
	 * cmd[1] = points
	 * cmd[2] = persona que terminó el juego
	 * cmd[3] = rival
	 * @param cmd
	 */
	public void sendScore(String[] cmd){
		String tag = "[scoreRival]:;" + cmd[1] + ";" + cmd[2] + ";" + cmd[3] + ";";
		try{
			for(int i = 0; i < players.size(); i++){	//Busca al rival
				if(players.get(i).getNickname().equals(cmd[3])){
					new DataOutputStream(sockets.get(i).getOutputStream()).writeUTF(tag); //Manda la respuesta
				}
			}
		}catch(Exception e){
			e.getMessage();
		}
	}
}
