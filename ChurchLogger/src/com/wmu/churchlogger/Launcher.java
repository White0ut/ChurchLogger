package com.wmu.churchlogger;

import java.sql.SQLException;

public class Launcher {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		ProgramManager manager = new ProgramManager();
		
		manager.start();
	}

}
