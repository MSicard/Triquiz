package com.triquiz.gui;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import com.triquiz.database.beans.Game;
import com.triquiz.socket.SocketManager;
import com.triquiz.tools.UserSocket;

//import javafx.scene.paint.Color;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class CombatGUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Color answer = new Color(216, 227, 217);
	Color letter2 = Color.black;
	Color pane = new Color(112, 157, 187);
	Color letter = Color.BLACK;
	Color pane2 = Color.white;
	Color good = new Color(214, 5, 0);
	Color bad = new Color(0, 168, 11);
	
	ArrayList<Game> game;
	int points;
	int count = 0;
	SocketManager sm;
	private WinLose frameW;

	
	/*
	 * Components
	 */
    private DecimalFormat timeFormatter;
    private byte centiseconds = 0;
    private byte seconds = 10;
    private static Timer timer;
	protected JPanel contentPane;
	protected JPanel panel;
	protected JButton btnBOption;
	protected JButton btnAOption;
	protected JButton btnCOption;
	protected JButton btnDOption;
	protected JLabel lblUser;
	protected JLabel lblRival;
	protected JLabel lblTime;
	protected JLabel lblVs;
	protected JTextArea textArea;
	
	protected JButton btnStart;
	
	
	UserSocket me;
	String rival;
	int typeCombat;

	/**
	 * Create the frame.
	 * Constructor que pide las preguntas
	 * @wbp.parser.constructor
	 */
	public CombatGUI(UserSocket me, String rival, int typeCombat) {
		/**
		 * @wbp.parser.constructor
		 */
		this.me = me;
		this.rival = rival;
		this.typeCombat = typeCombat;
		this.frameW = null;
		createWindow();
		dare();
	}
	/**
	 * Constructor que no pide las preguntas, que ya las recibe
	 * @param me
	 * @param rival
	 * @param typeCombat
	 * @param game
	 */
	public CombatGUI(UserSocket me, String rival, int typeCombat, ArrayList<Game> game){
		this.me = me;
		this.rival = rival;
		this.frameW = null;
		this.typeCombat = typeCombat;
		this.game = game;
		createWindow();
	}
	
	//crea la ventana
	private void createWindow(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(pane);
		setContentPane(contentPane);
		
		btnBOption = new JButton("B. Option");
		btnBOption.setBackground(answer);
		btnBOption.setForeground(letter2);
		btnBOption.addActionListener(this);
		
		panel = new JPanel();
		panel.setBackground(pane2);
		
		btnAOption = new JButton("A. Option");
		btnAOption.setBackground(answer);
		btnAOption.setForeground(letter2);
		btnAOption.addActionListener(this);
		
		btnCOption = new JButton("C. Option");
		btnCOption.setBackground(answer);
		btnCOption.setForeground(letter2);
		btnCOption.addActionListener(this);
		
		btnDOption = new JButton("D. Option");
		btnDOption.setBackground(answer);
		btnDOption.setForeground(letter2);
		btnDOption.addActionListener(this);
		
		lblUser = new JLabel(me.getUser().getNickname());
		lblUser.setForeground(letter);
		
		lblRival = new JLabel(rival);
		lblRival.setForeground(letter);
		
		lblTime = new JLabel("Time");
		
		
		lblVs = new JLabel("VS");
		lblVs.setForeground(letter);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(33)
							.addComponent(lblUser)
							.addGap(116)
							.addComponent(lblVs))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(98)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnCOption, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnAOption, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnDOption, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnBOption, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
							.addGap(0)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblRival)
									.addGap(67))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblTime)
									.addGap(46))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(btnStart)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblUser)
							.addComponent(lblRival))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblVs)
							.addGap(18)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAOption, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBOption, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCOption, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDOption, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnStart)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(38)
							.addComponent(lblTime)))
					.addContainerGap())
		);
		
		textArea = new JTextArea();
		textArea.setColumns(8);
		textArea.setRows(3);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
					.addContainerGap())
		);
		
	    timeFormatter = new DecimalFormat("00");
	    

	  
        timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                if (centiseconds > 0) {
                    centiseconds--;
                } else {
                    if (seconds == 0) {
                    	timeEnd(game.get(count));
                    } else if (seconds > 0) {
                        seconds--;
                        centiseconds = 99;
                    } 
                }
                lblTime.setText( timeFormatter.format(seconds));
			}
        });
        
        
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		CombatGUI.this.setResizable(false);
		panel.setMaximumSize(new Dimension(206, 69));
		panel.setMinimumSize(new Dimension(206, 69));
		textArea.setAlignmentX(CENTER_ALIGNMENT);
		textArea.setAlignmentY(CENTER_ALIGNMENT);
		
		
		btnAOption.setEnabled(false);
		btnBOption.setEnabled(false);
		btnCOption.setEnabled(false);
		btnDOption.setEnabled(false);
	}
	
	
	//Crear tipo partida de BATALLA
//	public void battle(){
//		SocketManager sm = new SocketManager(client);
//		ArrayList<Game> game = sm.getGameTable(typeCombat, user);
//		try{
//			if(!game.isEmpty()){
//				for(int i = 0; i < game.size(); i++){
//					labelText(game.get(i));
//					while(seconds == 0){
//						
//					}
//				}
//			}
//		}catch(Exception e){
//			
//		}
//		
//	}
	
	
	//Crear tipo de RETO
	
	public void dare(){
		//pedir el tipo de preguntas :)
		points = 0;
		SocketManager sm = new SocketManager(me.getClient());
		game = sm.getGameTable(typeCombat, rival, me.getUser());
		System.out.println(game.get(0).getText2() + " " +
		game.get(0).getText1() + " " + game.get(0).getText3() + " " + game.get(0).getText4());
	}
	
	//Poner la nueva pregunta con sus respectivas respuestas
	private void reloadGame(Game g){
		//Habilitar botones
		btnAOption.setBackground(answer);
		btnBOption.setBackground(answer);
		btnCOption.setBackground(answer);
		btnDOption.setBackground(answer);
		btnStart.setEnabled(false);
		btnAOption.setEnabled(true);
		btnBOption.setEnabled(true);
		btnCOption.setEnabled(true);
		btnDOption.setEnabled(true);
		seconds = 20;
		textArea.setText(g.getTextQuestion());
		textButton(btnBOption, g.getText2());
		textButton(btnAOption, g.getText1());
		textButton(btnCOption, g.getText3());
		textButton(btnDOption, g.getText4());

	}
	
	//parar el juego porque ya respondio
	private void stopGame(Game g, JButton button){
		timer.stop();	//parar el tiempo
		btnStart.setEnabled(true);	//hacer que no pueda presionar otro boton
		btnAOption.setEnabled(false);
		btnBOption.setEnabled(false);
		btnCOption.setEnabled(false);
		btnDOption.setEnabled(false);
		if(button.getText().equals(g.getCorrect())){	//respuesta correcta
			button.setBackground(good);
			points++;			//sumar puntos
		}
		else{	//se equiv� de respues
			button.setBackground(bad);
			incorrect(g); //buscar la respuesta correcta
		}
	}
	
	//encontrar la verdadera buena respuesta porque se equivoco
	private void incorrect(Game g){
		if(btnAOption.getText().equals(g.getCorrect())){
			btnAOption.setBackground(good);
		}
		else if(btnBOption.getText().equals(g.getCorrect())){
			btnBOption.setBackground(good);
		}
		else if(btnCOption.getText().equals(g.getCorrect())){
			btnCOption.setBackground(good);
		}
		else if(btnDOption.getText().equals(g.getCorrect())){
			btnDOption.setBackground(good);
		}
	}
	private void timeEnd(Game g){
		timer.stop();
		btnStart.setEnabled(true);	//hacer que no pueda presionar otro boton
		btnAOption.setEnabled(false);
		btnBOption.setEnabled(false);
		btnCOption.setEnabled(false);
		btnDOption.setEnabled(false);
		incorrect(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnStart)){		//que inicie la partida
			try{
				if(count < game.size() - 1){
					count++;				//sumar la cantidad de preguntar que llevas
					reloadGame(game.get(count));
					timer.start();
				}
				else{		//se termino, tienes que esperar resultados
					JOptionPane.showMessageDialog(null, "Ya terminaste la partida" + 
				"\nEstamos esperando los resultados de tu rival");
					//Envía[score]:;puntje;Yo;Rival;
					String tag = "[score]:;" + points + ";" + me.getUser().getNickname() + ";" + rival + ";";
					sm = new SocketManager(me.getClient());
					sm.score(tag);
					CombatGUI.this.openWinLose();
					CombatGUI.this.dispose();
				}
				}catch(Exception ex){
					System.out.println(ex.getMessage());
			}
				

		}
		else if(e.getSource().equals(btnAOption)){
			stopGame(game.get(count), btnAOption);
			
		}
		else if(e.getSource().equals(btnBOption)){
			stopGame(game.get(count), btnBOption);
		}
		else if(e.getSource().equals(btnCOption)){
			stopGame(game.get(count), btnCOption);
		}
		else if(e.getSource().equals(btnDOption)){
			stopGame(game.get(count), btnDOption);
		}
	}
	
	private void textButton(JButton button, String text){
		if(text.indexOf(" ") != -1 ){
			String[] text2 = text.split(" ");
			if(text2.length == 3 ){
				button.setText("<html><p>" + text2[0] + "</p><p>" + text2[1] + "</p><p>" + text2[2] + "</p></html>" );
			}else if(text2.length > 3){
				button.setText("<html><p>" + text2[0] + "</p><p>" + text2[1] + " " + text2[2] + "</p><p>" + text2[3] + "</p></html>" );
			}
			else{
				button.setText("<html><p>" + text2[0] + "</p><p>" + text2[1] + "</p></html>" );
			}
		}
		else{
			button.setText(text);
		}
	}
	
	public void frameWinLose(WinLose frameW){
		this.frameW = frameW;
		System.out.println(frameW);
		
	}
	
	public void openWinLose(){
		while(true){
			System.out.println(" ");
			if(frameW != null){
				System.out.println("Entro al if :V");
			frameW.compareScore(points);
			frameW.setVisible(true);
			break;
			}
		}

		}

	
}
