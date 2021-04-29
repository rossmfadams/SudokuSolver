package com.sudokuSolver;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;

public class CreateAccountControl implements ActionListener
{
	// Private data fields for the container and chat client.
	private JPanel container;
	private SudokuClient client;

	// Constructor for the create account controller.
	public CreateAccountControl(JPanel container, SudokuClient client)
	{
		this.container = container;
		this.client = client;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae)
	{
		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		// The Cancel button takes the user back to the initial panel.
		if (command == "Cancel")
		{
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "1");
		}

		// The Submit button creates a new account.
		else if (command == "Submit")
		{
			// Get the text the user entered in the three fields.
			CreateAccountPanel createAccountPanel = (CreateAccountPanel)container.getComponent(2);
			CreateAccountData data = new CreateAccountData(createAccountPanel.getUsername(), createAccountPanel.getPassword());

			// Check the validity of the information locally first.
			if (data.getUsername().equals("") || data.getPassword().equals(""))
			{
				displayError("You must enter a username and password.");
				return;
			}

			else if (!createAccountPanel.getPasswordVerify().equals(data.getPassword())) {
				displayError("Password fields must match.");
				return;
			}

			else if (data.getPassword().length() < 6) {
				displayError("Password too short.");
				return;
			}

			else { // Submit the account information to the server.
				try {
					client.sendToServer(data);
				} catch (IOException e) {
					displayError("Error connecting to the server.");
					e.printStackTrace();
				}
			}
		}
	}

	// After an account is created, set the User object and display the contacts screen.
	public void createAccountSuccess()
	{
		CreateAccountPanel createAccountPanel = (CreateAccountPanel)container.getComponent(2);
		createAccountPanel.setError("");
		CardLayout cardLayout = (CardLayout)container.getLayout();
		cardLayout.show(container, "4");
	}

	// Method that displays a message in the error label.
	public void displayError(String error)
	{
		CreateAccountPanel createAccountPanel = (CreateAccountPanel)container.getComponent(2);
		createAccountPanel.setError(error);
	}
}
