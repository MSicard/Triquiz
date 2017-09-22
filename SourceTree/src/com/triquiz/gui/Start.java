package com.triquiz.gui;


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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;

public class Start extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SocketManager sm;    
	Socket client = null;
	int port;
	
	private Color letter = new Color(156, 87, 0);
	private Color welcome = new Color(156, 87, 0);
	private Color pane = new Color(216, 227, 217);
	
	/*
	 * Components
	 */
	protected JPanel contentPane;
	protected JTextField textFieldUser;
    protected JPasswordField passwordFieldPass;
	protected JLabel lblWelcome;
	protected JLabel lblUser;
	protected JLabel lblPassword;
	protected JButton btnNewAccount;
	private JButton btnEnter;

	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Start(int port, Socket client) {
		setResizable(false);
		setTitle("Login");
		this.client = client;
		this.port = port;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(pane);
		
		lblWelcome = new JLabel("!Bienvenido!");
		lblWelcome.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblWelcome.setForeground(welcome);
		
		lblUser = new JLabel("Usuario");
		lblUser.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		lblUser.setForeground(letter);
		
		lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		lblPassword.setForeground(letter);

		
		textFieldUser = new JTextField("user1");
		textFieldUser.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldUser.setColumns(10);
		
		btnNewAccount = new JButton("Registrarse");
		btnNewAccount.addActionListener(this);
		
		passwordFieldPass = new JPasswordField("123");
		btnEnter = new JButton("Iniciar");
		btnEnter.addActionListener(this);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(171)
					.addComponent(lblWelcome)
					.addContainerGap(186, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(62)
							.addComponent(btnNewAccount))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(59)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUser)
								.addComponent(lblPassword))))
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnEnter)
						.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(passwordFieldPass, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textFieldUser, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))))
					.addGap(75))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addComponent(lblWelcome)
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUser))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordFieldPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewAccount)
						.addComponent(btnEnter))
					.addGap(23))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnEnter)){
			sm = new SocketManager(client);
			User user = sm.login(textFieldUser.getText(), String.valueOf(passwordFieldPass.getPassword()));
			if(user != null) {
                Connect connect  = new Connect(port, new UserSocket(client, user));
                connect.setVisible(true); 
                Start.this.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(this, "Error al inicar sesion\n" 
                	+ "Intente con un usuario o contrasena diferente","Aviso",JOptionPane.WARNING_MESSAGE);
            }
		}
		if(e.getSource().equals(btnNewAccount)){
			NewAccount frame = new NewAccount(port, client);
			frame.setVisible(true);
			Start.this.dispose();
		}
		
	}
}
