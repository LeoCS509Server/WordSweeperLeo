package admin.adminview;



import javax.swing.*;

import admin.adminmodel1.AdminModel;
import client.ServerAccess;

import java.applet.Applet;
import java.awt.*;

public class AdminApplication extends JFrame{
	private final JLabel lblPlayers = new JLabel("Player:");
	private JTextField scorearea;
	private JTextArea playerarea;
	private JTextArea gamearea;
	ServerAccess serverAccess;
	public final AdminModel model;
	
	public AdminApplication(AdminModel m){
		this.model = m;
		this.initComponents();
	}
	
	public void initComponents() {
		setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ActiveGames：");
		lblNewLabel.setBounds(6, 6, 450, 16);
		lblNewLabel.setBackground(Color.WHITE);
		getContentPane().add(lblNewLabel);
		lblPlayers.setBounds(180, 0, 98, 29);
		getContentPane().add(lblPlayers);
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setBounds(320, 6, 61, 16);
		getContentPane().add(lblScore);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 34, 90, 100);
		
		JTextArea textArea = new JTextArea();
//		textArea.setColumns(6);
		scrollPane.add(textArea);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(168, 34, 90, 100);
		getContentPane().add(scrollPane_3);
		
		JTextArea playerarea = new JTextArea();
		playerarea.setRows(8);
//		textArea_1.setColumns(6);
		scrollPane_3.setViewportView(playerarea);
		
		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.setBounds(16, 146, 68, 16);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(314, 34, 78, 26);
		getContentPane().add(scrollPane_1);
		
		scorearea = new JTextField();
		scrollPane_1.setViewportView(scorearea);	
		scorearea.setColumns(1);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(61, 178, 300, 300);
		getContentPane().add(scrollPane_4);
		
		JPanel panel = new JPanel();
		scrollPane_4.setViewportView(panel);
		panel.setLayout(null);
		
		Choice choice = new Choice();
		choice.setBounds(24, 38, 86, 96);
		getContentPane().add(choice);
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
//		this.pack();
		this.setSize(420, 520);
		this.setVisible(true);}
		
	
	
	    
	    
	    
	    
	    
		public JTextArea getgamearea(){
			return this.gamearea;
		}
		
		public JTextArea getplayerarea(){
			return this.playerarea;
		}
		
		public JTextField getscorearea(){
			return this.scorearea;
		}
		/**
	 	public static void main(String[] args) {
			new AdminApplication("Admin");
		}*/
		public void setServerAccess(ServerAccess access) {
			this.serverAccess = access;
		}
		
		/** Get the server access object. */
		public ServerAccess getServerAccess() {
			return serverAccess;
		}

}
