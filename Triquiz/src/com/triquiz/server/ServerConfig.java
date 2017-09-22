package com.triquiz.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ServerConfig extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtPort;
	JLabel lblPort;
	JButton btnStart;
	JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ServerConfig frame = new ServerConfig();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ServerConfig() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtPort = new JTextField();
		txtPort.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPort.setText("4444");
		txtPort.setColumns(10);
		
		lblPort = new JLabel("Puerto");
		
		btnStart = new JButton("Iniciar");
		btnStart.addActionListener(this);
		lblStatus = new JLabel("El servidor está corriendo");
        lblStatus.setVisible(false);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(71, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPort)
							.addGap(48)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtPort, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStatus))))
					.addGap(151))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(76)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPort))
					.addGap(18)
					.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addComponent(lblStatus)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnStart)){
			try {
	            int PORT = Integer.parseInt(txtPort.getText());
	            if(PORT >= 1000 && PORT <= 9000){
	                Thread server = new Thread(new TriquizServer(PORT));
	                server.start();
	                btnStart.setEnabled(false);
	                txtPort.setEnabled(false);
	                lblStatus.setVisible(true);
	            }
	            else{
	                JOptionPane.showMessageDialog(this, "El puerto debe estar entre 4000 y 9000");
	            }
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, "El puerto debe ser un número entero y positivo.");
	        }
		}
	}
}
