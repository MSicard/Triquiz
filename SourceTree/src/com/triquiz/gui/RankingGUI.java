package com.triquiz.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Image;
import com.triquiz.database.beans.Ranking;
import com.triquiz.database.beans.User;
import com.triquiz.socket.SocketManager;
import com.triquiz.tools.UserSocket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JDesktopPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class RankingGUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color letter = Color.BLACK;
	private Color point = new Color(8, 86, 137);
	private Color pane2 = new Color(216, 227, 217);
	private Color pane = new Color(112, 157, 187);
	
	/*
	 * Components
	 */
	protected JPanel contentPane;
	protected JPanel panel;
	protected JDesktopPane desktopPane;
	protected JLabel lblUser;
	protected JLabel lblImagen;
	protected JLabel lblRanking;
	protected JLabel lblNumber1;
	protected JLabel lblNumber2;
	protected JLabel lblNumber3;
	protected JLabel lblNumber4;
	protected JLabel lblCategory1;
	protected JLabel lblCategory2;
	protected JLabel lblCategory3;
	protected JLabel lblCategory4;
	protected JLabel lblPoints1;
	protected JLabel lblPoints2;
	protected JLabel lblPoints3;
	protected JLabel lblPoints4;
	protected JButton btnBack;
	protected ImageIcon icon;
	
	private UserSocket me;
	Connect connect;


	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public RankingGUI(UserSocket me, Connect connect) {
		setResizable(false);
		this.me = me;
		this.connect = connect;
		
		
		ImageIcon icono = new ImageIcon("boton3horizontal.jpg");
		
		  
		System.out.println("Entro al constuctor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(pane);
		
		desktopPane = new JDesktopPane();
		
		panel = new JPanel();
		panel.setBackground(pane2);
		
		btnBack = new JButton("Regresar");
		btnBack.addActionListener(this);
		btnBack.setBackground(Color.gray);
		btnBack.setForeground(point);
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(240)
							.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(35)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(65)
							.addComponent(btnBack)))
					.addContainerGap(68, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(16)
					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnBack)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		
		lblUser = new JLabel(me.getUser().getNickname());
		lblUser.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblUser.setForeground(point);
		
		lblImagen = new JLabel("");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(27)
							.addComponent(lblUser))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblImagen, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(23)
					.addComponent(lblImagen, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblUser)
					.addGap(28))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		lblCategory1 = new JLabel("New label");
		
		lblCategory2 = new JLabel("New label");
		
		lblCategory3 = new JLabel("New label");
		
		lblCategory4 = new JLabel("New label");
		
		lblPoints4 = new JLabel("Points1");
		
		lblPoints1 = new JLabel("Points2");
		
		lblPoints2 = new JLabel("Points3");
		
		lblPoints3 = new JLabel("Points4");
		
		
		lblRanking = new JLabel("Ranking.............................");
		

		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(21)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCategory4)
								.addComponent(lblCategory3)
								.addComponent(lblCategory2)
								.addComponent(lblCategory1))
							.addGap(30)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPoints1)
								.addComponent(lblPoints4)
								.addComponent(lblPoints3)
								.addComponent(lblPoints2)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRanking)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRanking)
					.addGap(7)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPoints4)
						.addComponent(lblCategory1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCategory2)
						.addComponent(lblPoints1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPoints2)
						.addComponent(lblCategory3))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPoints3)
						.addComponent(lblCategory4))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		

		
		//Actualiza Puntajes del jugador
		rankingScore();
		
		lblCategory1.setForeground(letter);
		
		lblCategory2.setForeground(letter);
		
		lblCategory3.setForeground(letter);
		
		lblCategory4.setForeground(letter);
		
		lblPoints4.setForeground(point);
		
		lblPoints1.setForeground(point);
		
		lblPoints2.setForeground(point);
	
		lblPoints3.setForeground(point);
		
		ImageIcon user = new ImageIcon(getClass().getResource("/image/user.png"));
		icon = new ImageIcon(user.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		lblImagen.setIcon(icon);
		
		System.out.println("Salgo del constructor");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnBack)){
//			Connect frame = new Connect(client, user);
//			frame.setVisible(true);
			connect.setVisible(true);
			RankingGUI.this.dispose();
		}
	}
	
	
	//Aqu� se cambia el nombre de los labels 
	public void rankingScore(){
		System.out.println("Entro a ranking score");
		//Se pide que le manden los rankings del jugador
		//Aqui debería crearse nuevo hilo
		SocketManager sm = new SocketManager(me.getClient());
		//Se regresa un arraylist con todos sus rankings
		ArrayList<Ranking> rank = sm.rank(me.getUser());
		System.out.println(rank);
		try{
			System.out.println(rank.isEmpty());
			if(!rank.isEmpty()){
				//Se acomoda de mayor a menor
				//Collections.sort(rank);
	
				lblCategory1.setText(category(rank.get(0).getIdCategory()));
				lblPoints1.setText(String.valueOf(rank.get(0).getScore()));
				
				lblCategory2.setText(category(rank.get(1).getIdCategory()));
				lblPoints2.setText(String.valueOf(rank.get(1).getScore()));
				
				lblCategory3.setText(category(rank.get(2).getIdCategory()));
				lblPoints3.setText(String.valueOf(rank.get(2).getScore()));
				
				lblCategory4.setText(category(rank.get(3).getIdCategory()));
				lblPoints4.setText(String.valueOf(rank.get(3).getScore()));
			}
			else{
				JOptionPane.showMessageDialog(this, "No se pudieron obtener los puntajes","Error",JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "troné" + e.getMessage());
		}
	}
	
	//Saber que categor�a es seg�n el id
	public String category(int idCategory){
		switch(idCategory){
			case 1:
				return "Programación";
			case 2:
				return "Ciencia";
			case 3:
				return "Matemáticas";
			case 4:
				return "Historia";		
			default:
				return "Error";
		}
	}
}
