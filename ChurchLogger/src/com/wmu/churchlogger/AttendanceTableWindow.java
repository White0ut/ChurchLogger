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
import javax.swing.JTable;

public class AttendanceTableWindow extends JFrame{
	private static final long serialVersionUID = 1L;

	private DBAccess database;
	private JTable table;
	private CheckboxTableModel model;

	/**
	 * Create the application.
	 */
	public AttendanceTableWindow(DBAccess database) {
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
		this.setBounds(100, 100, 477, 457);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		JScrollPane scrollPane = new JScrollPane();

		JButton btnFinishAdding = new JButton("Finish Adding");
		btnFinishAdding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Finished checking things, closing the window and getting all the names
				finishedAdding();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
								.addComponent(btnFinishAdding))
								.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnFinishAdding)
						.addContainerGap())
				);

		model = new CheckboxTableModel();
		table = new JTable(model);
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
	}

	public void finishedAdding(){
		for(String s : model.getSelectedNames())
			System.out.println(s);
	}

	public JFrame getFrame() {
		return this;
	}


}
