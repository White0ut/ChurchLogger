package com.wmu.churchlogger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

public class AddMemberWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField phoneNumberField;
	private JTextField emailField;
	private JTextField birthDateField;
	private JTextField JoinDateField;
	private JTextField addressField;
	private JTextField cityField;
	private JTextField stateField;
	private JTextField zipField;
	private JTextArea notesTextArea;

	private DBAccess database;

	/**
	 * Create the application.
	 */
	public AddMemberWindow(DBAccess database) {
		this.database = database;
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
		this.setBounds(100, 100, 450, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.BLACK);

		JButton btnAddMember = new JButton("Add member");
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAccountInfo();

			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblLastName = new JLabel("First Name");
		lblLastName.setFont(new Font("Dialog", Font.BOLD, 14));

		JLabel lblLast = new JLabel("Last Name");
		lblLast.setFont(new Font("Dialog", Font.BOLD, 14));

		firstNameField = new JTextField();
		firstNameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// Lose focus of first name field
			}
		});
		firstNameField.setColumns(10);

		lastNameField = new JTextField();
		lastNameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		lastNameField.setColumns(10);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Dialog", Font.BOLD, 14));

		phoneNumberField = new JTextField();
		phoneNumberField.setColumns(10);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 14));

		emailField = new JTextField();
		emailField.setColumns(10);

		JLabel lblJoinData = new JLabel("Birth Date");
		lblJoinData.setFont(new Font("Dialog", Font.BOLD, 14));

		birthDateField = new JTextField();
		birthDateField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String date = birthDateField.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date testDate = null;
				birthDateField.setForeground(Color.black);
				try{
					testDate = sdf.parse(date);
				}catch (ParseException pe){
					birthDateField.setForeground(Color.red);
				}
				if (!sdf.format(testDate).equals(date)) {
					birthDateField.setForeground(Color.red);
				}
			}
		});
		birthDateField.setColumns(10);

		JLabel lblBirthDate = new JLabel("Join Date");
		lblBirthDate.setFont(new Font("Dialog", Font.BOLD, 14));

		JoinDateField = new JTextField();
		JoinDateField.setColumns(10);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Dialog", Font.BOLD, 14));

		addressField = new JTextField();
		addressField.setColumns(10);

		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Dialog", Font.BOLD, 14));

		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Dialog", Font.BOLD, 14));

		cityField = new JTextField();
		cityField.setColumns(10);

		stateField = new JTextField();
		stateField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// lost focus stateField
				if(stateField.getText().length() != 2){
					stateField.setForeground(Color.red);
				}else{
					stateField.setForeground(Color.black);
				}
			}
		});
		stateField.setColumns(10);

		JLabel lblZip = new JLabel("Zip");
		lblZip.setFont(new Font("Dialog", Font.BOLD, 14));

		zipField = new JTextField();
		zipField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// lost focus of zipField
				NumberFormat formatter = NumberFormat.getInstance();
				ParsePosition pos = new ParsePosition(0);
				formatter.parse(zipField.getText(), pos);
				if(zipField.getText().length() != pos.getIndex()){
					zipField.setForeground(Color.red);
				}else{
					zipField.setForeground(Color.black);
				}
			}
		});
		zipField.setColumns(10);

		JLabel lblNotes = new JLabel("Notes");
		lblNotes.setFont(new Font("Dialog", Font.BOLD, 14));

		notesTextArea = new JTextArea();

		JButton addMemberButton = new JButton("Add member");
		addMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addMemberToData();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAddMember)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(6)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
												.addComponent(firstNameField, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(lblLast, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
														.addComponent(lastNameField, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)))
														.addGroup(gl_panel.createSequentialGroup()
																.addGap(6)
																.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_panel.createSequentialGroup()
																				.addComponent(cityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(ComponentPlacement.RELATED)
																				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																						.addComponent(stateField, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
																						.addComponent(lblState, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																								.addComponent(zipField, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
																								.addComponent(lblZip, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
																								.addComponent(lblNotes, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
																								.addComponent(notesTextArea, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
																								.addPreferredGap(ComponentPlacement.RELATED)
																								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																								.addGap(96))
																								.addGroup(gl_panel.createSequentialGroup()
																										.addGap(12)
																										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
																												.addComponent(emailField)
																												.addGroup(gl_panel.createSequentialGroup()
																														.addPreferredGap(ComponentPlacement.RELATED)
																														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																																.addGroup(gl_panel.createSequentialGroup()
																																		.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																																				.addComponent(lblJoinData, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
																																				.addComponent(birthDateField, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
																																				.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
																																				.addPreferredGap(ComponentPlacement.RELATED)
																																				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																																						.addGroup(gl_panel.createSequentialGroup()
																																								.addComponent(lblBirthDate, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
																																								.addGap(21))
																																								.addComponent(JoinDateField, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
																																								.addComponent(addressField, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
																																								.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
																																								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
																																								.addComponent(lblPhoneNumber, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
																																								.addComponent(phoneNumberField))
																																								.addContainerGap(109, Short.MAX_VALUE))
																																								.addGroup(gl_panel.createSequentialGroup()
																																										.addContainerGap()
																																										.addComponent(addMemberButton)
																																										.addContainerGap(322, Short.MAX_VALUE))
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(19)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLast, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lastNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(firstNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblPhoneNumber, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(phoneNumberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblJoinData, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblBirthDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
														.addComponent(birthDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(JoinDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(addressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_panel.createSequentialGroup()
																		.addGap(4)
																		.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
																		.addGroup(gl_panel.createSequentialGroup()
																				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																						.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
																						.addComponent(lblZip, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
																						.addComponent(lblState, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
																						.addPreferredGap(ComponentPlacement.RELATED)
																						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																								.addComponent(cityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																								.addComponent(zipField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																								.addComponent(stateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																								.addPreferredGap(ComponentPlacement.RELATED)
																								.addComponent(lblNotes, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																								.addComponent(notesTextArea, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)))
																								.addPreferredGap(ComponentPlacement.RELATED)
																								.addComponent(addMemberButton)
																								.addGap(110)
																								.addComponent(btnAddMember)
																								.addContainerGap())
				);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(50, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
						.addGap(44))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(31)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 506, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(63, Short.MAX_VALUE))
				);
		this.getContentPane().setLayout(groupLayout);
	}


	public void addMemberToData(){
		String joinDate  = ProgramManager.reformatDate(JoinDateField.getText());
		String birthDate = ProgramManager.reformatDate(birthDateField.getText());
		
		database.addMember(firstNameField.getText(), lastNameField.getText(), 0, phoneNumberField.getText(), 
				emailField.getText(), joinDate, birthDate, notesTextArea.getText(), 
				addressField.getText(), cityField.getText(), stateField.getText(), Integer.parseInt(zipField.getText()));
		
		//updates the default table model for the member list
		try {
			database.updateMemberTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ProgramManager.closeWindow(this);
	}
	

	public JFrame getFrame() {
		return this;
	}

	public void checkAccountInfo(){
		ProgramManager.closeWindow(this);
		ProgramManager.openWindow(new ChurchLoggerWindow(database));
	}
}
