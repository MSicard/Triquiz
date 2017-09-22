package com.triquiz.gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class IPConnect extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public int serverPort = 4444; //Puerto default
    public int clientPort = 4443;
    Socket client = null;
	/*
	 * Components
	 */
	private JTextField txtIP;
	private JTextField txtServerPort;
	
	private JLabel lblIp;
	private JLabel lblServerPort;
	private JButton btnAccept;
	private JTextField txtClientPort;
	
	public IPConnect() {
		setResizable(false);
		setTitle("Configuraci�n");
		
		lblIp = new JLabel("IP:");
		
		txtIP = new JTextField();
		txtIP.setText("192.168.1.65");
		txtIP.setColumns(10);
		
		lblServerPort = new JLabel("Puerto del Servidor:");
		
		txtServerPort = new JTextField();
		txtServerPort.setText("4444");
		txtServerPort.setColumns(10);
		
		btnAccept = new JButton("Aceptar");
		btnAccept.addActionListener(this);
		setBounds(100, 100, 384, 235);
		
		JLabel lblPortClient = new JLabel("Puerto del Cliente:");
		
		txtClientPort = new JTextField();
		txtClientPort.setText("4443");
		txtClientPort.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIp)
						.addComponent(txtIP, 235, 235, 235)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtServerPort, Alignment.LEADING)
								.addComponent(lblServerPort, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(56)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtClientPort, Alignment.LEADING)
								.addComponent(lblPortClient, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))))
					.addContainerGap(46, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(268, Short.MAX_VALUE)
					.addComponent(btnAccept)
					.addGap(29))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblIp)
					.addGap(18)
					.addComponent(txtIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblServerPort)
						.addComponent(lblPortClient))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtServerPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtClientPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnAccept)
					.addGap(89))
		);
		getContentPane().setLayout(groupLayout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAccept)){
			try{
				serverPort = Integer.parseInt(txtServerPort.getText());
				client = new Socket(txtIP.getText(), serverPort);
				IPConnect.this.setVisible(false);
				Start start = new Start(clientPort, client);
	            start.setVisible(true);
			}catch(NumberFormatException nE){
				JOptionPane.showMessageDialog(this, "El puerto debe ser numero.","Error",JOptionPane.ERROR_MESSAGE);
                return;
			}
			catch(Exception ex){
				System.out.println("Error: " + ex.getMessage());
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Ha ocurrido un error al intentar conectarse.","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}

		}
	}
}
