package com.triquiz.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.triquiz.database.beans.User;
import com.triquiz.socket.SocketManager;
import com.triquiz.tools.UserSocket;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.LayoutStyle.ComponentPlacement;

public class TypeCombat extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color pane = new Color(112, 157, 187);
	private Color letter = new Color(214, 119, 0);
	private Color combat = Color.LIGHT_GRAY;
	private Color letterCombat = new Color(214, 119, 0);
	
	/*
	 * Components
	 */
	protected JPanel contentPane;
	protected JLabel lblUser; 
	protected JLabel lblRival;
	protected JButton btnBack;
	protected JButton btnTypeCombat2;
	private JLabel lblVs;
	
	UserSocket me;
	String rival;
	DataOutputStream outToServer;
	SocketManager sm;
	Connect connect;


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TypeCombat frame = new TypeCombat();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TypeCombat(UserSocket me, String rival, Connect connect) {
		setResizable(false);
		this.connect = connect;
		this.me = me;
		this.rival = rival;
//		this.connect = connect;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(pane);
		
		lblUser = new JLabel(me.getUser().getNickname());
		lblUser.setForeground(letter);
		
		lblRival = new JLabel(rival);
		lblRival.setForeground(letter);
		
		btnBack = new JButton("Cancelar");
		btnBack.addActionListener(this);
		btnBack.setBackground(letter);
		
		btnTypeCombat2 = new JButton("Reto");
		btnTypeCombat2.setBackground(combat);
		btnTypeCombat2.setForeground(letterCombat);
		btnTypeCombat2.addActionListener(this);
		
		lblVs = new JLabel("Vs");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addComponent(btnBack)
					.addContainerGap(66, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addComponent(lblUser)
					.addGap(39)
					.addComponent(lblVs)
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addComponent(lblRival)
					.addGap(49))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(58)
					.addComponent(btnTypeCombat2, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(67, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRival)
						.addComponent(lblVs)
						.addComponent(lblUser))
					.addGap(45)
					.addComponent(btnTypeCombat2)
					.addGap(34)
					.addComponent(btnBack)
					.addGap(37))
		);
		contentPane.setLayout(gl_contentPane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Regresar a la pantalla principal
		if(e.getSource().equals(btnBack)){
//			Connect frame = new Connect(me);
//			frame.setVisible(true);
			connect.setVisible(true);
			TypeCombat.this.dispose();
		}
		else{
			try{
				//Se mandó un desafio reto
//				if(e.getSource().equals(btnTypeCombat1)){
//					//Un tag con challenge1, el nombre del rival a quien se le manda, y el nombre de quien lo manda
//					String tag = "[challenge]:;[reto]:;" + rival + ";" +  me.getUser().getNickname() + ";";
//					sm = new SocketManager(me.getClient());
//					sm.sendCombat(tag);
//				}
				//Se mandó un desafio batalla
				if(e.getSource().equals(btnTypeCombat2)){
					//Un tag con challenge2, el nombre del rival a quien se le manda, y el nombre de quien lo manda
					String tag = "[challenge]:;[batalla]:;" + rival + ";" + me.getUser().getNickname() + ";";
					sm = new SocketManager(me.getClient()); //Lo mandamos al SocketManager
					sm.sendCombat(tag);			
				}else{
					
				}
			}catch(Exception ex){
				System.out.println(ex.getStackTrace());
			}finally{										//Cuando se manda el reto, se regresa a la venta principal			
				TypeCombat.this.dispose();					
			}
		}

	}
	
	
}
