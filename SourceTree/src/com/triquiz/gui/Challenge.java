package com.triquiz.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.triquiz.database.beans.User;
import com.triquiz.socket.FromServer;
import com.triquiz.socket.SocketManager;
import com.triquiz.tools.UserSocket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Challenge extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color pane = new Color(247, 167, 65);
	private Color rival = Color.BLACK;
	private Color daresYou = Color.BLACK;
	private Color hellNo = new Color(214, 5, 0);
	private Color bringItOut = new Color(0, 168, 11);
	
	
	   
	/*
	 * Components
	 */
	protected JPanel contentPane;
	protected JPanel panel;
	protected JLabel lblRival;
	protected JLabel lblDaresYou;
	protected JLabel lblTypeCombat;
	protected JButton btnBringItOut;
	protected JButton btnHellNo; 
	
	//Conexi�n 
	public SocketManager sm;
	UserSocket me;
	String[] aux;
	DataOutputStream outToServer;
	Connect connect;

	/**
	 * Create the frame.
	 */
	public Challenge(UserSocket me, String[] aux, Connect connect) {
		setResizable(false);
		
		this.me = me;
		this.aux = aux;
		this.connect = connect;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(pane);
		setContentPane(contentPane);
		
		panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		lblRival = new JLabel(aux[3]);
		lblRival.setForeground(rival);
		
		lblDaresYou = new JLabel("te est\u00E1 reatando en:");
		lblDaresYou.setForeground(daresYou);
		
		lblTypeCombat = new JLabel();
		lblTypeCombat.setForeground(daresYou);
		if(aux[1] == "[reto]"){
			lblTypeCombat.setText("Reto");
		}
		else{
			lblTypeCombat.setText("Batalla");
		}
		
		btnBringItOut = new JButton("Aceptar");
		btnBringItOut.setBackground(bringItOut);
		btnBringItOut.addActionListener(this); 
		
		btnHellNo = new JButton("Huir");
		btnHellNo.addActionListener(this);
		btnHellNo.setBackground(hellNo);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(175)
					.addComponent(lblTypeCombat)
					.addContainerGap(190, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(70, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnBringItOut)
						.addComponent(lblRival))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(92)
							.addComponent(lblDaresYou)
							.addGap(42))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
							.addComponent(btnHellNo)
							.addGap(53))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRival)
						.addComponent(lblDaresYou))
					.addGap(18)
					.addComponent(lblTypeCombat)
					.addGap(34)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBringItOut)
						.addComponent(btnHellNo))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
	}
	
	//Aceptar el reto o no
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String tag = "";
		try{
			sm = new SocketManager(me.getClient());
			if(e.getSource() == btnBringItOut){	
				//Un tag con response, si se acepta, y a quien mand� el reto al principio
				tag = "[response]:;" + "[Si]:;" + aux[2] + ";" + aux[3] + ";";
				sm.answerCombat(tag);
				Challenge.this.dispose();
			}
			else if(e.getSource() == btnHellNo){
				//No se acept� la partida
				tag = "[response]:;" + "[No]:;" + aux[2] + ";" + aux[3] + ";";
				//Se le manda al SockerManager
				sm.answerCombat(tag);
				Challenge.this.dispose();
				connect.setVisible(true);
			}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

}
