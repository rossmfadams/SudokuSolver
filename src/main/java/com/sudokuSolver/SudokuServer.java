package com.sudokuSolver;

import java.awt.Color;
import java.io.IOException;
import javax.swing.*;

import org.bytedeco.javacpp.opencv_core.Mat;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class SudokuServer extends AbstractServer{

	private JLabel status;
	private JTextArea log;
	private Database database = new Database();
	
	public SudokuServer(int port) {
		super(port);
	}
	public void setDatabase(Database db) {
		this.database = db;
	}

	@Override
	protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		 
		if(arg0 instanceof Mat) {
			Mat colorimg = (Mat)arg0;
			Object result;
			result = SudokuSolver.run(colorimg);
			
			try {
				arg1.sendToClient(result);
			} catch (IOException e) {
				return;
			}
		}


	    // If we received LoginData, verify the account information.
	    if (arg0 instanceof LoginData)
	    {
	      // Check the username and password with the database.
	      LoginData data = (LoginData)arg0;
	      Object result;
	      if (database.verifyLogin(data.getUsername(), data.getPassword()))
	      {
	        result = "LoginSuccessful";
	        log.append("Client " + arg1.getId() + " successfully logged in as " + data.getUsername() + "\n");
	      }
	      else
	      {
	        result = new Error("The username and password are incorrect.", "Login");
	        log.append("Client " + arg1.getId() + " failed to log in\n");
	      }
	      
	      // Send the result to the client.
	      try
	      {
	        arg1.sendToClient(result);
	      }
	      catch (IOException e)
	      {
	        return;
	      }
	    }
	    
	    // If we received CreateAccountData, create a new account.
	    else if (arg0 instanceof CreateAccountData)
	    {
	      // Try to create the account.
	      CreateAccountData data = (CreateAccountData)arg0;
	      Object result;
	      if (!database.createNewAcccount(data.getUsername(), data.getPassword()))
	      {
	        result = "CreateAccountSuccessful";
	        log.append("Client " + arg1.getId() + " created a new account called " + data.getUsername() + "\n");
	      }
	      else
	      {
	        result = new Error("The username is already in use.", "CreateAccount");
	        log.append("Client " + arg1.getId() + " failed to create a new account\n");
	      }
	      
	      // Send the result to the client.
	      try
	      {
	        arg1.sendToClient(result);
	      }
	      catch (IOException e)
	      {
	        return;
	      }
	    }
	  
	    
	}
	
	public void setLog(JTextArea log)
	  {
	    this.log = log;
	  }
	  
	  public JTextArea getLog()
	  {
	    return log;
	  }
	  
	  public void setStatus(JLabel status)
	  {
	    this.status = status;
	  }
	  
	  public JLabel getStatus()
	  {
	    return status;
	  }
	
	protected void serverStarted() 
	  {
	    System.out.println("Server Started");
	    log.append("Server Started\n");
	  }
	  
	  protected void serverStopped() 
	  {
	    System.out.println("Server Stopped");
	    log.append("Server Stopped Accepting New Clients - Press Listen to Start Accepting New Clients\n");
	  }
	  
	  protected void serverClosed() 
	  {
	    System.out.println("Server and all current clients are closed - Press Listen to Restart");
	    log.append("Server and all current clients are closed - Press Listen to Restart\n");
	  }

	  
	  protected void clientConnected(ConnectionToClient client) 
	  {
	    System.out.println("Client Connected");
	    log.append("Client Connected\n");
	    status.setText("Connected");
	    status.setForeground(Color.green);
	  }
	
	protected void listeningException(Throwable exception) {
		
		status.setText("Exception occurred while listening");
	    //status.setForeground(Color.RED);
	    log.append("Listening exception: " + exception.getMessage() + "\n");
	    log.append("Press Listen to restart server\n");
	}
}
