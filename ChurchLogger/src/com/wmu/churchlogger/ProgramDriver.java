package com.wmu.churchlogger;

public class ProgramDriver {

	public static void main(String[] args) {
		DBAccess db = new DBAccess();
		String[] driverArgs = {"driver"};
		LoginWindow.main(driverArgs);
		
	}
}