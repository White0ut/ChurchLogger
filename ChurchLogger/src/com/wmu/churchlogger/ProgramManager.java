package com.wmu.churchlogger;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;

public class ProgramManager {
	
	//private static DBAccess database;

	/* Entry point */
	public static void main(String[] args) throws SQLException {
		//============== TEST PHONE NUMBER FIXER ===============
		System.out.println("Date fixer: " + reformatDate("11-29-1993"));
		//======================================================
		
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
	 * SwapDate takes in a date in mm/dd/yyyy format and switches it to
	 * 		yyyy-mm-dd.
	 * 
	 * DIVIDER MUST BE '-' OR THIS WON'T WORK 
	 */
	public static String reformatDate(String oldDate){
		String newDate = "";
		
		String[] dateArray = oldDate.split("-");
		String month = dateArray[0];
		String day   = dateArray[1];
		String year  = dateArray[2];
		
		return year + "-" + month + "-" + day;
	}
}





