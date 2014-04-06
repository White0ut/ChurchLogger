package com.wmu.churchlogger;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class LoginWindow {

	private JFrame frame;
	private JTextField passwordField;
	private JTextField usernameField;
	private JLabel label;
	private JLabel label_1;
	private JButton createAccountButton;
	private JTextField errorField;
	private JCheckBox chckbxRememberMe;
	
	private boolean isRemembered;
	

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If they can't use nimbus...
		}
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(new Color(0, 0, 0));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(51)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(41, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(66)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(25, Short.MAX_VALUE))
				);

		passwordField = new JPasswordField();
		passwordField.setColumns(10);

		usernameField = new JTextField();
		usernameField.setColumns(10);

		label = new JLabel("Username:");
		label.setFont(new Font("Dialog", Font.BOLD, 14));

		label_1 = new JLabel("Password:");
		label_1.setFont(new Font("Dialog", Font.BOLD, 14));

		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkForUser();
			}
		});
		loginButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		createAccountButton = new JButton("Create Account");
		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				launchNewUserWindow();
			}
		});

		errorField = new JTextField();
		errorField.setBorder(null);
		errorField.setColumns(10);
		errorField.setForeground(Color.red);
		errorField.setEditable(false);
		
		chckbxRememberMe = new JCheckBox("Remember me");
		chckbxRememberMe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				isRemembered = isRemembered == true ? false : true;
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(loginButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(createAccountButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(errorField, 173, 173, 173)
								.addComponent(chckbxRememberMe))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(22)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(17)
							.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(errorField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(loginButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(createAccountButton)
						.addComponent(chckbxRememberMe))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}

	public void launchNewUserWindow() {
		frame.setVisible(false);
		frame.dispose();
		ProgramManager.startNewUserLogin();
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void checkForUser(){
		String username = usernameField.getText();
		String password = passwordField.getText();
		if(username.equals("admin")){
			if(password.equals("password")){
				frame.setVisible(false);
				frame.dispose();
				ProgramManager.startMainWindow();
				return;
			}
		}
		errorField.setText("Incorret login credentials");
	}
}
