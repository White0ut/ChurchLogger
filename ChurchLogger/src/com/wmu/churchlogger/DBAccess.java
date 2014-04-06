package com.wmu.churchlogger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;


public class DBAccess {
	
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "CLogger";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "pastor";
    String password = "loggingrox";
    
    Connection connection;
	
	
	public DBAccess(){
		try {
			
			System.out.println("Connecting to Database...");
			
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url+dbName,userName,password);

			System.out.println("Connected...");
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Data needs to come in order
	 * @throws SQLException 
	 */
	@SuppressWarnings("null")
	public DefaultTableModel updateMemberTable() throws SQLException{
		DefaultTableModel tableModel = new DefaultTableModel();
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM members NATURAL JOIN info NATURAL JOIN member_address");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		String[] recordString = null;
		int i;
		
		
		//add column headers
		Object[] columnHeadings = new String[] {
				"First Name", "Last Name", "Phone", "Email", "Join Date", "Stree Address", "Zip" 
			};
		tableModel.setColumnIdentifiers(columnHeadings);
		
		while(rs.next()){
			for(i = 0; i < columnCount; i++){
				recordString[i] = rs.getNString(i + 1);
			}
			
			tableModel.addRow(recordString);
		}
		
		return tableModel;
		
	}
	
	public boolean compareUPass(String user_name, String password) {
		MessageDigest digest;
		try {
		
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes("UTF-8"));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
}
