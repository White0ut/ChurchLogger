package edu.wmich.cs;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess {

	String url = "jdbc:mysql://10.0.2.2:3306/";
	String dbName = "CLogger";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "pastor";
	String password = "loggingrox";

	Connection connection;
	
	public DBAccess() {
		
		try {
		
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url+dbName,userName,password);

		
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public int findMemberByName(String fname, String lname) throws SQLException{
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT mID FROM members WHERE fname = '" + fname + "' AND lname = '" + lname + "'");
		
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		
		String[] recordString = new String[1];
		int i;
		
		while(rs.next()){
			for(i = 0; i < columnCount; i++){
				try{
				recordString[i] = rs.getString(i + 1);
				}
				catch(NullPointerException e){
					recordString[i] = "Null";
				}
			}
		}
		Integer retVal = new Integer(0);
		return retVal.valueOf(recordString[0]).intValue();
	}
	
	/**
	 * Marks the member present for the service date passed.
	 *
	 * @param mID Member ID for the church member.
	 * @param date Date of the church service attended. Date format MM/DD/YYYY
	 */
	public void addAttendanceForMember(String fname,String lname, String date){
		Statement st;
		
		try{
			st = connection.createStatement();
			
			String tempDate = reformatDate(date);
			String executeString = "INSERT INTO attendance (mID, service_date, cID) VALUES ((SELECT mID FROM members WHERE fname = '"+fname+"' and lname = '"+lname+"'), '" + tempDate + "', '0')";
			System.out.println(executeString);
			st.execute(executeString);
			st.close();
		}
		catch(SQLException e){
			System.out.println("SQL Exception: " + e);
		}
	}
	
	public String[] getMessages() {
		Statement stmt;
		String query = "SELECT message FROM church_message";
		String[] res = new String[countMessages()];
		try {
		    stmt = connection.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    int i = 0;
		    while (rs.next()) {
		        String msg = rs.getString("message");
		        res[i] = msg;
		        i++;
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {}
		return res;
	}
	
	public int countMessages() {
		Statement stmt;
		String query = "SELECT COUNT(message) FROM church_message";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			int count = rs.getInt(1);
			
			stmt.close();
			return count;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  0;
		
	}
	
	public void closeDBConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * reformatDate takes in a date in mm-dd-yyyy format and switches it to
	 * yyyy-mm-dd in order for it to be entered into a MySQL command.
	 * 
	 * DIVIDER MUST BE '-' OR THIS WON'T WORK.
	 * 
	 * @param oldDate The date with a normal format of mm-dd-yyyy or mm/dd/yyyy
	 */
	public static String reformatDate(String oldDate){
		
		String[] dateArray = oldDate.split("(-)|(/)");
		String month = dateArray[0];
		String day   = dateArray[1];
		String year  = dateArray[2];
		
		return year + "-" + month + "-" + day;
	}

}
