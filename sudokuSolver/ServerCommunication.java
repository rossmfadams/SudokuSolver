package sudokuSolver;

import java.awt.Color;

import javax.swing.*;

import lab7out.Database;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class ServerCommunication extends AbstractServer{

	private JLabel status;
	private JTextArea log;
	private Database database = new Database();
	
	public ServerCommunication(int port) {
		super(port);
	}

	@Override
	protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		 
		
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
	    //log.append("Server Stopped Accepting New Clients - Press Listen to Start Accepting New Clients\n");
	  }
	  
	  protected void serverClosed() 
	  {
	    System.out.println("Server and all current clients are closed - Press Listen to Restart");
	    //log.append("Server and all current clients are closed - Press Listen to Restart\n");
	  }

	  
	  protected void clientConnected(ConnectionToClient client) 
	  {
	    System.out.println("Client Connected");
	    log.append("Client Connected\n");
	    status.setText("Connected");
	    status.setForeground(Color.green);
	  }
	
	protected void listeningException(Throwable exception) {
		
		 System.out.println("Listening Exception:" + exception);
		    exception.printStackTrace();
		    System.out.println(exception.getMessage());
	}
}
