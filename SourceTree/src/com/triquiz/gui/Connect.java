package com.triquiz.gui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.triquiz.database.beans.Game;
import com.triquiz.database.beans.User;
import com.triquiz.socket.ClientServer;
import com.triquiz.tools.UserSocket;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Connect extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color pane = new Color(112, 157, 187);
	private Color pane2 = Color.WHITE;
	private Color button = new Color(247, 167, 65);
	private String[] users = {};
	/*
	 * Components
	 */
	protected JPanel contentPane;
	protected JPanel panel;
	private JButton btnUser;
	private JList<String> list;
	
	UserSocket me;
	int port;
	private TypeCombat frameTC;
	private CombatGUI frameC;

	
	/**
	 * Launch the application.
	 */
	public Connect(UserSocket me){
		this.me = me;
		userList(users);
		window();

	}
	public Connect(int port, UserSocket me) {
		
		//Constructor
		this.me = me;
		this.port = port;
		userList(users);
		
		Thread fromServer = new Thread(new ClientServer(port, me, this));
		fromServer.start();
		window();
		
	}
	
	public void window(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(pane);
		setContentPane(contentPane);
		
		
		panel = new JPanel();
		panel.setBackground(pane2);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(16)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(75, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(list, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
					.addGap(99))
		);
		
		JLabel lblImage = new JLabel(me.getUser().getNickname());
		
		btnUser = new JButton();
		btnUser.addActionListener(this);
		btnUser.setBackground(button);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnUser, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblImage))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(130, Short.MAX_VALUE)
					.addComponent(lblImage)
					.addGap(50)
					.addComponent(btnUser)
					.addGap(36))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		ImageIcon user = new ImageIcon(getClass().getResource("/image/user.png"));
		ImageIcon icon = new ImageIcon(user.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		btnUser.setIcon(icon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnUser)){
			RankingGUI frame = new RankingGUI(me, Connect.this);
			frame.setVisible(true);
			Connect.this.setVisible(false);
		}
		
	}
	//Crea la lista con los usuarios que estï¿½n conectados
	public void userList(String[] update){
		//conseguimos la lista del update
		users = update;
		//agregamos los elementos a la lista. 
		list = new JList<String>(users);
		list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(list));
		list.addListSelectionListener(
				//Si se selecciona alguien de la lista, se manda a la ventana TypeCombat para mandar un desafï¿½o
				new ListSelectionListener(){
					public void valueChanged(ListSelectionEvent event){
						frameTC = new TypeCombat(me, list.getSelectedValue(), Connect.this);
						frameTC.setVisible(true);
//						Connect.this.dispose();
						Connect.this.setVisible(false);
					}

				});
	}
	
	public void updateList(String[] update){
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(int i = 0; i < update.length; i++){
			model.addElement(update[i]);
		}
		users = update;
		//Revisar
		System.out.println(list);

		list.removeAll();
		list.setModel(model);
	}
	
	//Funciï¿½n para decir que se estï¿½ esperando una respuesta del otro jugador
	void warning(){
		JOptionPane.showMessageDialog(this, "Estamos esperando respuesta del contricante");
	}
	
	public void startGame(String[] aux){
		System.out.println(aux);
		   if(aux[1].equals("[Si]:")){
			   System.out.println("Nombre del rival en connect: " + aux[2]);
			   frameC = new CombatGUI(me, aux[2], 0);
			   frameC.setVisible(true);
			   frameTC.dispose();
			   
		   }else{
			   JOptionPane.showMessageDialog(null, "Se rechazó tu partida :C");
			   frameTC.dispose();
			   Connect.this.setVisible(true);
		   }
	}
	
	public void startGameRival(String rival, ArrayList<Game> game){
		System.out.println("Llego a crear partida del enemigo");
		frameC = new CombatGUI(me, rival, 0, game);
		frameC.setVisible(true);
		
	}
	
	public void createWinLose(int pointsR, String rival){
		WinLose frameW = new WinLose(me.getUser().getNickname(), rival, pointsR, Connect.this);
		frameW.setVisible(false);
		frameC.frameWinLose(frameW);
	}
}
