package com.triquiz.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.triquiz.database.beans.User;
import com.triquiz.socket.SocketManager;
import com.triquiz.tools.UserSocket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class NewAccount extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SocketManager sm;    
	Socket client = null;
	int port;
	
	private Color pane = new Color(216, 227, 217);
	
	/*
	 * Components
	 */
	protected JPanel contentPane;
	protected JTextField txtUser;
	protected JPasswordField txtConfirmPassword;
	protected JPasswordField txtPassword;
	protected JLabel lblUser;
	protected JLabel lblPassword;
	protected JLabel lblRepeatPassword;
	protected JLabel lblNewAccount;
	protected JButton btnCreate;
	protected JButton btnBack;
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewAccount frame = new NewAccount();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 */
	/**
	 * Create the frame.
	 */
	public NewAccount(int port, Socket client) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.client = client;
		this.port = port;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(pane);;
		setContentPane(contentPane);
		
		lblUser = new JLabel("Usuario");
		
		lblPassword = new JLabel("Contrase\u00F1a");
		
		lblRepeatPassword = new JLabel("Repetir Contrase\u00F1a");
		
		txtUser = new JTextField();
		txtUser.setColumns(10);
		
		txtConfirmPassword = new JPasswordField();
		
		txtPassword = new JPasswordField();
		
		btnCreate = new JButton("Crear");
		btnCreate.addActionListener(this);
		
		btnBack = new JButton("Regresar");
		btnBack.addActionListener(this);
		
		lblNewAccount = new JLabel("Nueva Cuenta");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(83)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblUser)
						.addComponent(lblRepeatPassword)
						.addComponent(lblPassword))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtConfirmPassword, Alignment.TRAILING)
						.addComponent(txtPassword, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
						.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(103, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewAccount)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnBack)
							.addPreferredGap(ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
							.addComponent(btnCreate)
							.addGap(26))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewAccount)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUser))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtConfirmPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRepeatPassword))
					.addGap(50)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreate)
						.addComponent(btnBack))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnCreate)){
			sm = new SocketManager(client);//Se crea una nueva instancia del SocketManager con el socket actual
			if(String.valueOf(txtPassword.getPassword()).equals(String.valueOf(txtConfirmPassword.getPassword()))){//Si las contraseñas coinciden:
				User user = sm.register(txtUser.getText(), String.valueOf(txtPassword.getPassword()));
				if(user != null){ //Se envia al socketManager una pedición de registro
					//Si regresa verdadero el socketmanager se encargará de hacer el login
					 Connect connect  = new Connect(port, new UserSocket(client, user));
		             connect.setVisible(true);
		             NewAccount.this.dispose();
				}
				else{
	                JOptionPane.showMessageDialog(this, "Error al crear usuario\n" 
	                	+ "Intente con un usuario o contraseña diferente","Aviso",JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource().equals(btnBack)){
			Start frame = new Start(port, client);
			frame.setVisible(true);
			NewAccount.this.setVisible(false);
		}
	}
}
