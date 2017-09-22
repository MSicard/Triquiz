package com.triquiz.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JButton;

public class WinLose extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color pane = new Color(247, 70, 65);
	private Color pane2 = new Color(112, 157, 187);
	private Color wins = Color.white;
	private Color lose = Color.white;
	private Color rematch = Color.gray;
	private Color menu = new Color(8, 86, 137);
	
	/*
	 * Components
	 */
	protected JPanel contentPane;
	protected JPanel panel;
	protected JPanel panel_1;
	protected JButton btnMenu;
	protected JLabel lblLose;
	protected JLabel lblUser2;
	protected JLabel lblImage2;
	protected JLabel lblWins;
	protected JLabel lblUser;
	protected JLabel lblImage;
	protected ImageIcon icon;
	protected ImageIcon icon2;
	protected ImageIcon icon3;

	protected int rivalScore;
	protected int myScore;
	protected String rival;
	protected String me;
	protected Connect connect;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public WinLose(String me, String rival, int score, Connect connect) {
		setResizable(false);
		this.rivalScore = score;
		this.connect = connect;
		this.rival = rival;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(pane);
		setContentPane(contentPane);
		
	    panel = new JPanel();
		panel.setBackground(pane2);
		
		panel_1 = new JPanel();
		panel_1.setBackground(pane2);
		
		btnMenu = new JButton("Menu");
		btnMenu.setBackground(menu);
		btnMenu.addActionListener(this);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(180)
							.addComponent(btnMenu)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnMenu)
					.addGap(5))
		);
		
		lblLose = new JLabel("Perdedor");
		lblLose.setForeground(lose);
		
		lblUser2 = new JLabel(rival);
		lblUser2.setForeground(wins);
		
		lblImage2 = new JLabel("");
		lblImage2.setForeground(wins);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(75)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUser2)
								.addComponent(lblLose)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(53)
							.addComponent(lblImage2, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(53, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLose)
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addComponent(lblImage2, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblUser2)
					.addGap(36))
		);
		panel_1.setLayout(gl_panel_1);
		
		lblWins = new JLabel("Has ganado!");
		lblWins.setForeground(wins);
		
		lblUser = new JLabel(me);
		lblUser.setForeground(wins);
		
		lblImage = new JLabel("");
		lblImage.setForeground(wins);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(64)
							.addComponent(lblUser))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(72)
							.addComponent(lblWins)))
					.addContainerGap(31, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(47)
					.addComponent(lblImage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(46))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblWins)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblImage, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblUser)
					.addGap(35))
		);
		
		ImageIcon win = new ImageIcon(getClass().getResource("/image/images.png"));
		icon = new ImageIcon(win.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		ImageIcon lose = new ImageIcon(getClass().getResource("/image/lose.png"));
		icon2 = new ImageIcon(lose.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		ImageIcon tie = new ImageIcon(getClass().getResource("/image/empate.png"));
		icon2 = new ImageIcon(tie.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnMenu)){
			connect.setVisible(true);
			WinLose.this.dispose();
		}
	}
	
	//Llegaron mis resultados
	public void compareScore(int score){
		this.myScore = score;
		if(myScore < rivalScore){
			lblWins.setText("Has perdido");
			lblLose.setText("Tu rival ha ganado");
			lblImage.setIcon(icon2);
			lblImage2.setIcon(icon);
			
		}
		else if(myScore > rivalScore){
			lblWins.setText("Has ganado, ¡Felicidades!");
			lblLose.setText("Tu rival ha perdido");
			lblImage.setIcon(icon);
			lblImage2.setIcon(icon2);
		}
		else if(myScore == rivalScore){
			lblWins.setText("Empate");
			lblLose.setText("Empate");
			lblImage.setIcon(icon3);
			lblImage2.setIcon(icon3);
		}
	}
}
