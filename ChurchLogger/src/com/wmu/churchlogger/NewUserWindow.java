package com.wmu.churchlogger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

public class NewUserWindow {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;


	/**
	 * Create the application.
	 */
	public NewUserWindow() {
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
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.BLACK);
		
		JLabel label_1 = new JLabel("Username:");
		label_1.setFont(new Font("Dialog", Font.BOLD, 14));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton button_1 = new JButton("Create Account");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAccountInfo();
				
			}
		});
		
		JLabel label = new JLabel("Password:");
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		
		JLabel lblRetypePassword = new JLabel("Retype Password:");
		lblRetypePassword.setFont(new Font("Dialog", Font.BOLD, 14));
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(10);
		
		JCheckBox chckbxSubscribeForChurchlogger = new JCheckBox("Subscribe for ChurchLogger Newsletter");
		
		JCheckBox chckbxRememberUsnameAnd = new JCheckBox("Remember Username and Password");
		
		JCheckBox chckbxAgreeTo = new JCheckBox("Agree to terms of service");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(button_1))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_1))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRetypePassword))
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(passwordField, Alignment.LEADING)
							.addComponent(passwordField_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
						.addComponent(chckbxSubscribeForChurchlogger)
						.addComponent(chckbxRememberUsnameAnd, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxAgreeTo, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRetypePassword, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(chckbxSubscribeForChurchlogger)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxRememberUsnameAnd)
					.addGap(18)
					.addComponent(chckbxAgreeTo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_1)
					.addContainerGap())
		);
		
		JTextArea txtrWhatIsUp = new JTextArea();
		txtrWhatIsUp.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtrWhatIsUp.setLineWrap(true);
		txtrWhatIsUp.setText(getTerms());
		scrollPane.setViewportView(txtrWhatIsUp);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(48, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
					.addGap(44))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(31)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 506, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	private String getTerms() {
		BufferedReader br = null;
		String line = "", solution = "";
		try {
		
			br = new BufferedReader(new FileReader("assets/TermsOfAgreement.txt"));
			
			while((line = br.readLine()) != null) {
				solution += line+"\n";
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return solution;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void checkAccountInfo(){
		frame.setVisible(false);
		frame.dispose();
		ProgramManager.startMainWindow();
	}
}
