package com.wmu.churchlogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
//		addMember("Addison", "Webb", 0, "(269) 615-5084", "addison.f.webb@gmail.com", "2014-04-08", "1991-11-28", "NA", "8380 Copper Harbor St.", "Kalamazoo", "MI", 49009);
//		addMember("Kendrick", "AWSguy", 0, "(207) 798-0903", "kendrick.cline@wmich.edu", "2014-04-08", "1992-1-1", "Cool guy", "1234 Road St.", "Kalamazoo", "MI", 49009);
//		addMember("Dillon", "Toughguy", 0, "(517) 974-3360", "dillon.burton@wmich.com", "2014-04-08", "1991-1-1", "Cool guy", "5678 Another St.", "Kalamazoo", "MI", 49009);
//		addMember("Josh", "MYSQLguy", 0, "(989) 430-6826", "josh.dkyourlastname@wmich.com", "2014-04-08", "1991-1-1", "Cool guy", "9012 Long Rd.", "Kalamazoo", "MI", 49009);
//		addMember("Jim", "Joe", 0, "(269) 615-5084", "supguys@gmail.com", "2014-04-08", "1991-11-28", "NA", "8380 Copper Harbor St.", "Kalamazoo", "MI", 49009);
//		addMember("Ken", "Cool", 0, "(207) 798-0903", "hello@wmich.edu", "2014-04-08", "1992-1-1", "Cool guy", "1234 Road St.", "Kalamazoo", "MI", 49009);
//		addMember("Don", "Do", 0, "(517) 974-3360", "dillon.burton@wmich.com", "2014-04-08", "1991-1-1", "Cool guy", "5678 Another St.", "Kalamazoo", "MI", 49009);
//		addMember("John", "Smith", 0, "(989) 430-6826", "josh.dkyourlastname@wmich.com", "2014-04-08", "1991-1-1", "Cool guy", "9012 Long Rd.", "Kalamazoo", "MI", 49009);
		
		int amount = 150, count;
		String[] firstNames = new String[amount];
		String[] lastNames = new String[amount];
		String[] emails = new String[amount];
		String[] joinDates = new String[amount];
		String[] birthDates = new String[amount];
		String[] cities = new String[amount];
		String[] zipcodes = new String[amount];
		String[] phone = new String[amount];
		
		String[] add = new String[amount];
		
		
		Scanner input = null;
		try {input = new Scanner(new FileReader("names.txt"));
		} catch (FileNotFoundException e) {e.printStackTrace();}
		count = 0;
		while(count < amount){
			String[] names = input.nextLine().split("\t ");
			System.out.println(names[0]);
			firstNames[count] = names[0];
			lastNames[count] = names[1];
			count++;
		}
		
		input.close();
		
		try {input = new Scanner(new FileReader("emails.txt"));
		} catch (FileNotFoundException e) {e.printStackTrace();}
		count = 0;
		while(count < amount){
			String email = input.nextLine();
			int r = 0 + (int)(Math.random() * ((3 - 0) + 1));
			if(r == 0){
				emails[count] = email + "@gmail.com";
			}else if(r == 1){
				emails[count] = email + "@yahoo.com";
			}else if(r == 2){
				emails[count] = email + "@outlook.com";
			}else{
				emails[count] = email + "@webmail.com";
			}
			count++;
		}
		
		input.close();
		
		try {input = new Scanner(new FileReader("join.txt"));
		} catch (FileNotFoundException e) {e.printStackTrace();}
		count = 0;
		while(count < amount){
			String join = input.nextLine();
			joinDates[count] = join;
			count++;
		}
		
		input.close();
		
		try {input = new Scanner(new FileReader("birth.txt"));
		} catch (FileNotFoundException e) {e.printStackTrace();}
		count = 0;
		while(count < amount){
			String birth = input.nextLine();
			birthDates[count] = birth;
			count++;
		}
		
		input.close();
		
		count = 0;
		while(count < amount){
			int r = 0 + (int)(Math.random() * ((1 - 0) + 1));
			if(r == 0){
				cities[count] = "Portage";
				zipcodes[count] = "49024";
			}else{
				zipcodes[count] = "49008";
				cities[count] = "Kalamazoo";
			}
			String phoneString = "(269) ";
			for(int index = 0; index < 7; index++){
				int n = 0 + (int)(Math.random() * ((9 - 0) + 1));
				phoneString += n;
				if(index == 2){
					phoneString += "-";
				}
			}
			phone[count] = phoneString;
			add[count] = "nothing now";
			count++;
		}
		
		count = 0;
		while(count < amount){
			addMember(firstNames[count], lastNames[count], 0, phone[count], emails[count], joinDates[count], birthDates[count], "Note", add[count], cities[count], "MI", Integer.parseInt(zipcodes[count]));
		count++;
		}
		
	}
	
	
	/*PUBLIC DATABASE MANIPULATION METHODS
	=======================================================================*/
	/**
	 * Creates the table model for the member list UI.
	 * @return the table model to be used to populate a UI table.
	 * @throws SQLException
	 */
	public DefaultTableModel updateMemberTable() throws SQLException{
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
	 * Updates the member list for attendance selection.
	 * @return
	 * @throws SQLException
	 */
	public Object[][] updateMemberList() throws SQLException{
		//Object[][] retObject = new Object[][];
		List<Object[]> complicatedObject = new ArrayList<Object[]>();	
		
		//initialize result set object
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT fname, lname FROM members");
		
		//initialize metadata object
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		int r = 0, c = 0;
		Object[] intermediateObj = new Object[3];
		
		while(rs.next()){
			for(c = 0; c < columnCount; c++){
				try{
					 intermediateObj[c]= rs.getString(c + 1);
				}
				catch(NullPointerException e){
					intermediateObj[c] = "Null";
				}
				intermediateObj[c+1] = new Boolean(false);	//sets the default state for attendance check box
			}
			complicatedObject.add(new Object[] {intermediateObj[0], intermediateObj[1], intermediateObj[2]});
			r++;
		}	

		int newr = r;
		int newc = 3;
		
		Object[][] retObject = new Object[newr][newc];
		
		for(r = 0; r < newr; r++){
			for(c = 0; c < newc; c++){
				retObject[r][c] = complicatedObject.get(r)[c];
			}
		}
		
		return retObject;
	}
	
	/**
	 * Creates the table model for the attendance list in the attendance tab.
	 * @return tableModel the model that has the list of attendance dates.
	 * @throws SQLException 
	 */
	public DefaultTableModel updateAttendanceTable() throws SQLException{
		DefaultTableModel tableModel = new DefaultTableModel();
		
		//insert update code here
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT DISTINCT date FROM attendance");
		
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		
		String[] recordString = new String[1];
		int i;
		
		Object[] columnHeadings = new String[]{
				"Date"
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
	 * @param joinDate MM/DD/YYYY
	 * @param birthDate MM/DD/YYYY
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
			st.close();
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
			st.close();
		}
		catch(SQLException e){
			System.out.println("SQL Exception: " + e);
		}
	}
	
	// shouldn't need this
	public void removeMember(int mID, int cID){
		//
	}
	
	/**
	 * Marks the member present for the service date passed.
	 *
	 * @param mID Member ID for the church member.
	 * @param date Date of the church service attended. Date format MM/DD/YYYY
	 */
	public void addAttendanceForMember(int mID, String date){
		Statement st;
		
		try{
			st = connection.createStatement();
			
			st.execute("INSERT INTO attendance (mID, date, cID) VALUES (" + mID + ", " + date + ", 0)");
			st.close();
		}
		catch(SQLException e){
			System.out.println("SQL Exception: " + e);
		}
	}
	
	public void closeDBConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
