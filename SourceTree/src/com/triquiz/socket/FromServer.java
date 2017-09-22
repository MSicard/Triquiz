package com.triquiz.socket;

import java.io.*;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.sun.java.swing.plaf.windows.resources.windows;
import com.triquiz.database.beans.User;
import com.triquiz.gui.Challenge;
import com.triquiz.gui.CombatGUI;
import com.triquiz.gui.Connect;
import com.triquiz.gui.Start;
import com.triquiz.tools.UserSocket;

public class FromServer implements Runnable{
    DataOutputStream outToServer;
    DataInputStream inFromServer;
    Connect mainWindow;
    UserSocket me;
    public FromServer(UserSocket me, Connect window){
    	try{
    		this.me = me;
    		mainWindow = window;
            outToServer = new DataOutputStream(me.getClient().getOutputStream());
            inFromServer = new DataInputStream(me.getClient().getInputStream());	
    	}catch(Exception e){
            JOptionPane.showMessageDialog(null, "Se perdio la coneccion..","Error",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
    	}
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
	       String fromServer = "";
	       try{
	    	   while(true){
	    		   fromServer = inFromServer.readUTF();
	    		   System.out.println("Recibido fromServer: " + fromServer);
	    		   if(!fromServer.equals("")){
	    			   String[] aux = fromServer.split(";");
	    			   switch(aux[0]){
	    			   case "[updatelist]:":
	    				   String[] aux2 = new String[aux.length - 1];
	    				   for(int i = 1; i < aux.length; i++){
	    					   aux2[i - 1] = aux[i];
	    				   }
	    				   mainWindow.updateList(aux2);
	    				   break;
	    			   case "[logout]:":
	    				   System.exit(0);
	    				   break;
	    			   case "[challenge]:":
	    				   Challenge challenge = new Challenge(me, aux, mainWindow);
	    				   challenge.setVisible(true);
	    				   mainWindow.setVisible(false);
	    				   break;    
	    			   case "[response]:":
	    				   if(aux[1] == "[Si]:"){
	    					 //  CombatGUI frame = new CombatGUI(client, aux[2], aux[3], 0);
	    					 //  frame.setVisible(true);
	    						JOptionPane.showMessageDialog(null, "Aqui comienza el juego :c");
	    					   
	    				   }
	    				   break;
	    			   }
	    		   }
	    		   else{
	    			   
	    		   }
	    	   }
	       }catch(IOException e){
	    	   JOptionPane.showMessageDialog(null, e.getStackTrace());
	       }
	}
	
	

}
