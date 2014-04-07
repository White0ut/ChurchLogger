package com.wmu.churchlogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

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
		}
		finally {
			//what is this for? -Addison
			
		}
		
		//build the database!!
		try {
			this.buildDatabaseSchema();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeDBConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return status of execution
	 */
	public boolean initializeDBFromScratch() {
		
		return true;
	}
	
	/**
	 * Data needs to come in order
	 * @throws SQLException 
	 */
	@SuppressWarnings("null")
	public DefaultTableModel updateMemberTable() throws SQLException{
		System.out.println("DBAccess: updateMemberTable running");
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
				recordString[i] = rs.getString(i + 1);
				System.out.println(recordString[i]);
			}
			
			tableModel.addRow(recordString);
		}
		
		return tableModel;
	}
	
	private byte[] getPW(String user_name) {
		
		return null;
	}
	
	public boolean compareUPass(String user_name, String password) {
		MessageDigest digest;
		try {
		
			digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(generateSalt());
			byte[] hashedBytes = digest.digest(password.getBytes("UTF-8"));
			
			if(Arrays.equals(hashedBytes, getPW(user_name))) {
				
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes;
    }
	
	/**
	 * All rights to: Omry Yadan
	 * @param conn
	 * @param in
	 * @throws SQLException
	 * @throws FileNotFoundException 
	 */
	public void buildDatabaseSchema() throws SQLException
	{
		InputStream in;
		
		try {
		
		in = new FileInputStream(new File("assets/scripts/createTablesChurchLogger.sql"));

		Scanner s = new Scanner(in);
		s.useDelimiter("(;(\r)?\n)|(--\n)");
		Statement st = null;
		try
		{
			st = connection.createStatement();
			while (s.hasNext())
			{
				String line = s.next();
				if (line.startsWith("/*!") && line.endsWith("*/"))
				{
					int i = line.indexOf(' ');
					line = line.substring(i + 1, line.length() - " */".length());
				}

				if (line.trim().length() > 0)
				{
					st.execute(line);
				}
			}
		}
		finally {
			if (st != null) st.close();
		} 
		} catch (FileNotFoundException e) {
		System.err.println("File missing from assets/scripts");
		e.printStackTrace();
		}
	}
}
