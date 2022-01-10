/**
 * 
 */
package serverPackage;

import java.io.*;
import java.net.*;
/**
 * @author champejo
 *
 */
public class TCPServer extends Thread {
    
    private static int maxClients = 10;	 
    
    /**
     * Maximum de connexions client autorises 
    */
    private int maxConnexions;	 
    
    private int numeroPort;	
    
    
    
    
    /**
     * constructors
     * @param unNumeroPort
    */
    public  TCPServer(int unNumeroPort) {
        
        numeroPort = unNumeroPort;
        maxConnexions = maxClients;
    }

    
    public String toString() {
        
        return "[TCP Server] on Port : " +  numeroPort ;
    }
    
    public void run() {
        int nbConnexions = 0;
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        
        try {
        	serverSocket = new ServerSocket ( numeroPort );
        } catch (IOException e) {
        	System.out.println("Could not listen on port: " + numeroPort + ", " + e);
        	System.exit(1);
        }
        
        
        /* On autorise maxConnexions traitements*/
        while (nbConnexions <= maxConnexions) {
        	try {
        		System.out.println(" Attente du serveur pour la communication d'un client " );
        		clientSocket = serverSocket.accept();
        		nbConnexions ++;
        		System.out.println("Nb connexions = " + nbConnexions);
        		
        	} catch (IOException e) {
        		System.out.println("Accept failed: " + serverSocket.getLocalPort() + ", " + e);
        		System.exit(1);
        	}
        	ConnectedClientThread st = new ConnectedClientThread( clientSocket , this );
        	st.start();
        }
        System.out.println("Deja " + nbConnexions + " clients. Maximum autorisé atteint");
        
        try {
        	serverSocket.close();
        } catch (IOException e) {
        	System.out.println("Could not close");
        }
        
     
    }
    

}

