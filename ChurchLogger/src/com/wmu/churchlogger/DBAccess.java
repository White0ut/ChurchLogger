package com.wmu.churchlogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;


public class DBAccess {
	
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "CLogger";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "pastor";
    String password = "loggingrox";
    
    Connection connection;
	
	/*CONSTRUCTOR METHOD
	=======================================================================*/
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @return status of execution
	 */
	public boolean initializeDBFromScratch() {
		return true;
	}
	
	/*AUTHENTICATION METHODS
	=======================================================================*/
	
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
	
	private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes;
    }
	
	
	/*DATABASE INITIALIZATION METHODS
	=======================================================================*/
	/**
	 * @param conn
	 * @param in
	 * @throws SQLException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */

	public void buildDatabaseSchema() throws SQLException, IOException {
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
			in.close();
			
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
		System.out.println("About to add Addison.");
		addMember("Addison", "Webb", 0, "(269) 615-5084", "addison.f.webb@gmail.com", "2014-04-08", "1991-11-28", "NA", "8380 Copper Harbor St.", "Kalamazoo", "MI", 49009);
		addMember("Kendrick", "AWSguy", 0, "(207) 798-0903", "kendrick.cline@wmich.edu", "2014-04-08", "1992-1-1", "Cool guy", "1234 Road St.", "Kalamazoo", "MI", 49009);
		addMember("Dillon", "Toughguy", 0, "(517) 974-3360", "dillon.burton@wmich.com", "2014-04-08", "1991-1-1", "Cool guy", "5678 Another St.", "Kalamazoo", "MI", 49009);
		addMember("Josh", "MYSQLguy", 0, "(989) 430-6826", "josh.dkyourlastname@wmich.com", "2014-04-08", "1991-1-1", "Cool guy", "9012 Long Rd.", "Kalamazoo", "MI", 49009);
	}
	
	
	/*PUBLIC DATABASE MANIPULATION METHODS
	=======================================================================*/
	/**
	 * Creates the table model for the member list UI.
	 * @return the table model to be used to populate a UI table.
	 * @throws SQLException
	 */
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
		
		String[] recordString = new String[13];
		int i;
		
		//add column headers
		Object[] columnHeadings = new String[] {
				"First Name", "Last Name", "Phone", "Email", "Join Date", "Stree Address", "Zip" 
		};
		
		tableModel.setColumnIdentifiers(columnHeadings);
		
		while(rs.next()){
			for(i = 0; i < columnCount; i++){
				try{
				recordString[i] = rs.getString(i + 1);
				}
				catch(NullPointerException e){
					recordString[i] = "Null";
				}
			}
			
			tableModel.addRow(recordString);
		}
		return tableModel;
	}
	
	/**
	 * Adds member to database. All parameters must be passed. 
	 * @param firstName Full first name.
	 * @param lastName Full last name.
	 * @param churchID 3 digit church code.
	 * @param phone Format: (123) 555-1234
	 * @param email Full email address.
	 * @param joinDate YYYY-MM-DD
	 * @param birthDate YYYY-MM-DD
	 * @param note Any notes about the member.
	 * @param strAddress Full street address.
	 * @param city Full city name.
	 * @param state 2 character state code.
	 * @param zip Must be 5 digit number.
	 */
	public void addMember(String firstName, String lastName, int churchID, String phone, String email, String joinDate, String birthDate, String note, String strAddress, String city, String state, int zip){
		Statement st = null;
		
		try{
			st = connection.createStatement();
			
			st.execute("BEGIN");
			st.execute("INSERT INTO members (fname, lname, cID) VALUES ('" + firstName + "', '" + lastName + "', '" + churchID + "')");
			st.execute("SELECT LAST_INSERT_ID() INTO @lastID");
			st.execute("INSERT INTO info (mID, phone, email, join_date, birth_date, note) VALUES (@lastID, '" + phone + "', '" + email + "', '" + joinDate + "', '" + birthDate + "', '" + note + "')");
			st.execute("INSERT INTO member_address (mID, straddress, city, state, zip) VALUES (@lastID, '" + strAddress + "', '" + city + "', '" + state + "', '" + zip + "')");
			st.execute("COMMIT");
		}
		catch(SQLException e){
			System.out.println("SQL Exception: " + e);
		}
	}
	/**
	 * Removes member from database. All parameters must be passed. 
	 * @param firstName Full first name.
	 * @param lastName Full last name.
	 */
	public void removeMember(String firstName, String lastName){
		Statement st;
		
		try{
			st = connection.createStatement();
			
			st.execute("DELETE FROM members " +
					   "WHERE fname = '" + firstName + 
					   "' AND lname = '" + lastName + "';");
		}
		catch(SQLException e){
			System.out.println("SQL Exception: " + e);
		}
	}
	
	
	public void removeMember(int mID, int cID){
		//
	}
	
	public void closeDBConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
