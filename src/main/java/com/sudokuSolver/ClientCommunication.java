package com.sudokuSolver;

import com.sudokuSolver.CreateAccountControl;
import com.sudokuSolver.LoginControl;
import ocsf.client.AbstractClient;

public class ClientCommunication extends AbstractClient{

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
	public ClientCommunication(String host, int port) {
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
