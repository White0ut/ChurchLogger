package com.wmu.churchlogger;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;

public class ProgramManager {
	
	//private static DBAccess database;

	/* Entry point */
	public static void main(String[] args) throws SQLException {		
		openWindow(new LoginWindow());
	}

	/**
	 * openWindow opens the JFrame specified
	 * @param window is the window you wish to open
	 */
	public static void openWindow(final JFrame window){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				window.setVisible(true);
			}
		});
	}

	/**
	 * closeWindow closes the JFrame specified
	 * @param window is the window you wish to close
	 */
	public static void closeWindow(JFrame window){
		window.setVisible(false);
		window.dispose();
	}
	
	/**
	 * reformatDate takes in a date in mm-dd-yyyy format and switches it to
	 * yyyy-mm-dd in order for it to be entered into a MySQL command.
	 * 
	 * DIVIDER MUST BE '-' OR THIS WON'T WORK.
	 * 
	 * @param oldDate The date with a normal format of mm-dd-yyyy or mm/dd/yyyy
	 */
	public static String reformatDate(String oldDate){
		
		String[] dateArray = oldDate.split("(-)|(/)");
		String month = dateArray[0];
		String day   = dateArray[1];
		String year  = dateArray[2];
		
		return year + "-" + month + "-" + day;
	}
}





