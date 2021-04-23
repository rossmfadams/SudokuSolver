package com.sudokuSolver;

import java.io.Serializable;

public class LoginData implements Serializable 
{
	// Private data fields for the username and password.
	private String username;
	private String password;

	//Constructor that initializes the username and password.
	public LoginData(String username, String password)
	{
		setUsername(username);
		setPassword(password);
	}

	// Getter for the username
	public String getUsername()
	{
		return username;
	}

	//Getter for the password
	public String getPassword()
	{
		return password;
	}

	//Setter for the username 
	public void setUsername(String username)
	{
		this.username = username;
	}

	//Setter for the password 
	public void setPassword(String password)
	{
		this.password = password;
	}


}