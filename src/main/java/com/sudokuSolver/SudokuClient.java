package com.sudokuSolver;

import ocsf.client.AbstractClient;

public class SudokuClient extends AbstractClient{

	private LoginControl loginControl;
	private CreateAccountControl createAccountControl;

	public void setLoginControl(LoginControl loginControl)
	{
		this.loginControl = loginControl;
	}
	public void setCreateAccountControl(CreateAccountControl createAccountControl)
	{
		this.createAccountControl = createAccountControl;
	}
	public SudokuClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object arg0) {


		if (arg0 instanceof String)
		{
			// Get the text of the message.
			String message = (String)arg0;

			// If we successfully logged in, tell the login controller.
			if (message.equals("LoginSuccessful"))
			{
				loginControl.loginSuccess();
			}

			// If we successfully created an account, tell the create account controller.
			else if (message.equals("CreateAccountSuccessful"))
			{
				createAccountControl.createAccountSuccess();
			}
			//for error
			else if (arg0 instanceof Error)
		    {
		      // Get the Error object.
		      Error error = (Error)arg0;
		      
		      // Display login errors using the login controller.
		      if (error.getType().equals("Login"))
		      {
		        loginControl.displayError(error.getMessage());
		      }
		      
		      // Display account creation errors using the create account controller.
		      else if (error.getType().equals("CreateAccount"))
		      {
		        createAccountControl.displayError(error.getMessage());
		      }
		    }
		}
	}

	public void connectionException (Throwable exception) 
	{

		System.out.println("Message exception occured.");
		System.out.println(exception.getMessage());
		exception.printStackTrace();
	}
	public void connectionEstablished()
	{

		System.out.println("Connection established");
	}

}
