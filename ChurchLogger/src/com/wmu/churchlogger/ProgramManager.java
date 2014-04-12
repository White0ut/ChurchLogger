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
}
