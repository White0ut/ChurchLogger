package com.wmu.churchlogger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

public class LoginWindow extends JFrame{
	private static final long serialVersionUID = 1L;

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
	 * Initialize the contents of the this.
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
		this.getContentPane().setBackground(new Color(255, 255, 255));
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(new Color(0, 0, 0));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
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
		this.getContentPane().setLayout(groupLayout);
	}

	public void launchNewUserWindow() {
		ProgramManager.closeWindow(this);
		ProgramManager.openWindow(new NewUserWindow());
	}

	public JFrame getFrame() {
		return this;
	}

	public void checkForUser(){
		String username = usernameField.getText();
		String password = passwordField.getText();
		if(username.equals("admin")){
			if(password.equals("password")){
				ProgramManager.closeWindow(this);
				ProgramManager.openWindow(new ChurchLoggerWindow());
				return;
			}
		}
		errorField.setText("Incorret login credentials");
	}
}
