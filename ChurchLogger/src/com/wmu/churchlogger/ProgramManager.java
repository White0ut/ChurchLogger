package com.wmu.churchlogger;

import java.awt.EventQueue;

public class ProgramManager {
	
	static Runnable loginWindows, mainWindow;
	
	public ProgramManager() {
		
	}

	/**
	 * @param args
	 */
	public void start() {
		
		startLogin();
		
	}
	
	private static void startLogin() {
		
		EventQueue.invokeLater(loginWindows = new Runnable() {
			public void run() {
					LoginWindow window = new LoginWindow();
					window.getFrame().setVisible(true);
			}
		});
	}
	
	public static void startNewUserLogin() {

		EventQueue.invokeLater(loginWindows = new Runnable() {

			@Override
			public void run() {
				NewUserWindow window = new NewUserWindow();
				window.getFrame().setVisible(true);
			}
		});
	}
	
	public static void startMainWindow() {
		loginWindows = null;
		EventQueue.invokeLater(mainWindow = new Runnable() {

			@Override
			public void run() {
				ChurchLoggerWindow window = new ChurchLoggerWindow();
				window.getFrame().setVisible(true);
			}
			
		});
	}
	
	
}
