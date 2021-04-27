package com.sudokuSolver;

import java.sql.*;
import java.util.*;
import java.io.*;

public class Database
{
	private Connection conn;
	//Add any other data fields you like � at least a Connection object is mandatory
	public Database()
	{

		// Read from the properties file
		Properties prop = new Properties();

		// Create a FileInputStream to read from the db.properties
		try {
			FileInputStream fis = new FileInputStream("db.properties");
			try {
				prop.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Read user, pass, url
		String user = prop.getProperty("user");
		String pass = prop.getProperty("password");
		String url = prop.getProperty("url");

		// Create connection object
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public ArrayList<String> query(String query)
	{
		// Declare empty ArrayList
		ArrayList<String> result = new ArrayList<String>();
		try {

			// Create Statement
			Statement statement = conn.createStatement();

			// Invoke query
			ResultSet rs = statement.executeQuery(query);

			//Check for empty ResultSet
			if (!rs.next()) {
				return null;
			}
			else {
				// Get metadata for # of columns
				ResultSetMetaData rmd = rs.getMetaData();
				int numColumns = rmd.getColumnCount();

				// Outer loop gets each row
				int i = 1;

				do {
					String record = "";
					for (i = 1; i <= numColumns; i++) {
						record += rs.getString(i);
						if (i < numColumns) {
							record += ",";
						}
					}

					result.add(record);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}

		return result;
	}

	public void executeDML(String dml) throws SQLException
	{
		// Create a statement
		Statement statement = conn.createStatement();

		// Execute the statement
		statement.execute(dml);
	}

	//Searches the Database for duplicate usernames
	public boolean createNewAcccount(String user, String pass) {
		// Select username from user where username='user'
		ArrayList<String> result;
		String selectQuery = "select username from user where username = '" + user +"';";
		result = this.query(selectQuery);

		// Store the new username and encrypted password in User table;
		if(result.equals(null))
		{
			String insertQuery = "insert into user values('" + user + "', aes_encrypt('" + pass + "', 'key'));";
			this.query(insertQuery);

			return true;	
		}
		return false;
	}

	// Determines if username and password match
	public boolean verifyLogin(String user, String pass) {
		// Search Database for Username and Password
		ArrayList<String> result;
		String selectQuery = "select username, aes_decrypt(password, 'key') from user where username = '" + user +"';";
		result = this.query(selectQuery);
		String verifiedPass = result.get(0);

		if(verifiedPass.equals(pass)) {
			return true;
		}
		return false; 
	}

	public void setConnection(String string) {
		// TODO Auto-generated method stub
		  Connection conn = null;
		  Properties prop = new Properties();
		  String user = prop.getProperty("user");
		  String pass = prop.getProperty("password");
		  String url = prop.getProperty("url");
		  try {
		conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
		  this.conn = conn;
	}

	public Connection getConnection() {
		// TODO Auto-generated method stub
		return conn;
	}

}

