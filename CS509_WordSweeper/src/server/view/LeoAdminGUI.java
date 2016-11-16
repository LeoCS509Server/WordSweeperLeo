package server.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.ServerAccess;
import server.model.Model;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dimension;

public class LeoAdminGUI extends JFrame {
	/**GUI maintains reference to Model for ease of navigation*/
	public final Model model;
	private JPanel contentPane;
	private JScrollPane globalBoardPanel;
	ServerAccess serverAccess;
	JScrollPane requestArea;
	JScrollPane responseArea;

	/**
	 * Create the frame.
	 */
	public LeoAdminGUI(Model model) {
		this.model = model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 767, 648);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane statePanel = new JScrollPane();
		
		globalBoardPanel = new JScrollPane();
		globalBoardPanel.setSize(new Dimension(140, 140));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(statePanel, GroupLayout.PREFERRED_SIZE, 688, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(58)
					.addComponent(globalBoardPanel, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(344, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(statePanel, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(globalBoardPanel, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
					.addGap(18))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public Dimension getGlobalBoardPanelSize() {
		return globalBoardPanel.getSize();
	}
	public void setGlobalBoardPanelSize(Dimension size) {
		globalBoardPanel.setSize(size);
	}
	
	/** Navigation access to actionable elements in the GUI. */
	public JScrollPane getRequestArea() {
		return requestArea;
	}
	/** Navigation access to actionable elements in the GUI. */
	public JScrollPane getResponseArea() {
		return responseArea;
	}
	/** Get the server access object. */
	public ServerAccess getServerAccess() {
		return serverAccess;
	}
	
}
