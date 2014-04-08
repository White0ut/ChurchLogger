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
		
		//initialize result set object
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT fname, lname, phone, email, join_date, straddress, zip"
				+ " FROM members NATURAL JOIN info NATURAL JOIN member_address");
		
		//initialize metadata object
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		
		System.out.println("column count: " + rsmd.getColumnCount());
		
		String[] recordString = new String[13];
		int i;
		
		//add column headers
		Object[] columnHeadings = new String[] {
				"First Name", "Last Name", "Phone", "Email", "Join Date", "Stree Address", "Zip" 
		};
		
		tableModel.setColumnIdentifiers(columnHeadings);
		
		System.out.println("Right before parsing of result set");
		while(rs.next()){
			for(i = 0; i < columnCount; i++){
				System.out.println(i);
				try{
				recordString[i] = rs.getString(i + 1);
				}
				catch(NullPointerException e){
					recordString[i] = "Null";
				}
				System.out.println(recordString[i]);
			}
			
			tableModel.addRow(recordString);
		}
		System.out.println("after parsing");
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
	public void buildDatabaseSchema() throws SQLException {
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
			
			fillDatabase();
			
			s.close();
		}
		finally {
			if (st != null) st.close();
		} 
		} catch (FileNotFoundException e) {
		System.err.println("File missing from assets/scripts");
		e.printStackTrace();
		}
		
	}
	
	/**
	 * Fills the database with example data
	 * @throws SQLException
	 */
	private void fillDatabase() throws SQLException {
		Statement st = null; 
		st = connection.createStatement();
		
		st.execute("BEGIN");
		st.execute("INSERT INTO members (cID, fname, lname) VALUES ('42', 'Addison', 'Webb')");
		st.execute("SELECT LAST_INSERT_ID() INTO @lastID");
		st.execute("INSERT INTO info (mID, phone, email, join_date, birth_date, note) VALUES (@lastID, '2696155084', 'test@website.com', '4/8/14', '4/8/14', 'none')");
		st.execute("INSERT INTO member_address (mID, straddress, city, state, zip) VALUES (@lastID, '8380 Copper Harbor St.', 'Kalamazoo', 'MI', '49009')");
		st.execute("COMMIT");
		
		st.execute("BEGIN");
		st.execute("INSERT INTO members (cID, fname, lname) VALUES ('42', 'Ben', 'Baronie')");
		st.execute("SELECT LAST_INSERT_ID() INTO @lastID");
		st.execute("INSERT INTO info (mID, phone, email, join_date, birth_date, note) VALUES (@lastID, '2696155084', 'test@website.com', '4/8/14', '4/8/14', 'none')");
		st.execute("INSERT INTO member_address (mID, straddress, city, state, zip) VALUES (@lastID, '8380 Copper Harbor St.', 'Kalamazoo', 'MI', '49009')");
		st.execute("COMMIT");
		
		st.execute("BEGIN");
		st.execute("INSERT INTO members (cID, fname, lname) VALUES ('42', 'Derek', 'Jeter')");
		st.execute("SELECT LAST_INSERT_ID() INTO @lastID");
		st.execute("INSERT INTO info (mID, phone, email, join_date, birth_date, note) VALUES (@lastID, '2696155084', 'test@website.com', '4/8/14', '4/8/14', 'none')");
		st.execute("INSERT INTO member_address (mID, straddress, city, state, zip) VALUES (@lastID, '8380 Copper Harbor St.', 'Kalamazoo', 'MI', '49009')");
		st.execute("COMMIT");
		
		st.execute("BEGIN");
		st.execute("INSERT INTO members (cID, fname, lname) VALUES ('42', 'Mariano', 'Raveria')");
		st.execute("SELECT LAST_INSERT_ID() INTO @lastID");
		st.execute("INSERT INTO info (mID, phone, email, join_date, birth_date, note) VALUES (@lastID, '2696155084', 'test@website.com', '4/8/14', '4/8/14', 'none')");
		st.execute("INSERT INTO member_address (mID, straddress, city, state, zip) VALUES (@lastID, '8380 Copper Harbor St.', 'Kalamazoo', 'MI', '49009')");
		st.execute("COMMIT");
		
		st.execute("BEGIN");
		st.execute("INSERT INTO members (cID, fname, lname) VALUES ('42', 'Joe', 'Dirt')");
		st.execute("SELECT LAST_INSERT_ID() INTO @lastID");
		st.execute("INSERT INTO info (mID, phone, email, join_date, birth_date, note) VALUES (@lastID, '2696155084', 'test@website.com', '4/8/14', '4/8/14', 'none')");
		st.execute("INSERT INTO member_address (mID, straddress, city, state, zip) VALUES (@lastID, '8380 Copper Harbor St.', 'Kalamazoo', 'MI', '49009')");
		st.execute("COMMIT");
		
		st.execute("BEGIN");
		st.execute("INSERT INTO members (cID, fname, lname) VALUES ('42', 'Benjamin', 'Button')");
		st.execute("SELECT LAST_INSERT_ID() INTO @lastID");
		st.execute("INSERT INTO info (mID, phone, email, join_date, birth_date, note) VALUES (@lastID, '2696155084', 'bb@website.com', '4/8/14', '4/8/14', 'none')");
		st.execute("INSERT INTO member_address (mID, straddress, city, state, zip) VALUES (@lastID, '8380 Copper Harbor St.', 'Kalamazoo', 'MI', '49009')");
		st.execute("COMMIT");
		
		st.execute("BEGIN");
		st.execute("INSERT INTO members (cID, fname, lname) VALUES ('42', 'The', 'Muffin Man')");
		st.execute("SELECT LAST_INSERT_ID() INTO @lastID");
		st.execute("INSERT INTO info (mID, phone, email, join_date, birth_date, note) VALUES (@lastID, '555-8907', 'mm@website.com', '4/8/14', '4/8/14', 'none')");
		st.execute("INSERT INTO member_address (mID, straddress, city, state, zip) VALUES (@lastID, '8380 Copper Harbor St.', 'Kalamazoo', 'MI', '49009')");
		st.execute("COMMIT");
		
		st.execute("BEGIN");
		st.execute("INSERT INTO members (cID, fname, lname) VALUES ('42', 'Charlie', 'Sheen')");
		st.execute("SELECT LAST_INSERT_ID() INTO @lastID");
		st.execute("INSERT INTO info (mID, phone, email, join_date, birth_date, note) VALUES (@lastID, '2696155084', 'csheen@website.com', '4/8/14', '4/8/14', 'none')");
		st.execute("INSERT INTO member_address (mID, straddress, city, state, zip) VALUES (@lastID, '8380 Copper Harbor St.', 'Kalamazoo', 'MI', '49009')");
		st.execute("COMMIT");
	}
}
