package lab2out;

import javax.swing.*;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class ServerCommunication extends AbstractServer{

	private JLabel status;
	public ServerCommunication(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void setStatus(JLabel status) {
		
	}
	
	public void serverStarted() {
		
	}

	public void serverClosed() {
		
	}
	
	public void clientConnected() {
		
	}
	public void serverStopped() {
		
	}
	
	protected void listeningException(Throwable exception) {
		exception.printStackTrace();
	}
}
