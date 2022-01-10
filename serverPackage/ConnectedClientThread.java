package serverPackage;

import java.io.*;
import java.net.*;


public class ConnectedClientThread extends Thread {

	private Socket clientSocket;
	private TCPServer myServer;
	
	public ConnectedClientThread( Socket aClientSocket , TCPServer aServer ) {
		clientSocket = aClientSocket;
		myServer = aServer;
	}
	
    public void run() {
    	String inputReq;

		try {
			/* Ouverture des objets de type Stream sur la socket du client réseau  */
			BufferedReader  is = new BufferedReader ( new InputStreamReader (clientSocket.getInputStream()));
			PrintStream os = new PrintStream(clientSocket.getOutputStream());
			
			System.out.println( "Client Thread " );
			
			if ( (inputReq = is.readLine()) != null)  {
				System.out.println( " Msg 2 Recu " + inputReq );
				String chaines[] = inputReq.split( " " );
				
				for( int i = 0 ; i < chaines.length ; i++ ) 
						System.out.println( " Indice : " + i + " Mot : " + chaines[i] );
			}
			clientSocket.close();
			os.close();
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

    	
    }
}
