package com.wmu.churchlogger;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DateWindow extends JFrame {
	private JTable members;

	/**
	 * Create the application.
	 */

	public Object[][] model;

	public DateWindow(Object[][] newModel) {
		this.model = newModel;
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
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane();

		members = new JTable();
		scrollPane.setViewportView(members);
		DefaultTableModel dm = new DefaultTableModel();
		// add column headers
		Object[] columnHeadings = new String[] { "First Name", "Last Name" };
		

		dm.setColumnIdentifiers(columnHeadings);
		for(Object[] o : model){
			String[] recordString = new String[2];
			for(int index = 0; index < recordString.length; index++){
				recordString[index] = (String)o[index];
				//System.out.println("Added " + recordString[index]);
			}dm.addRow(recordString);
		}
		
		members.setModel(dm);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finish();
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(319, Short.MAX_VALUE)
					.addComponent(btnOk)
					.addContainerGap())
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnOk)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
	
	public void finish(){
		ProgramManager.closeWindow(this);
	}

}
