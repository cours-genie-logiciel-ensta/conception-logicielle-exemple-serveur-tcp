/**
 * 
 */
package serverPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Représente le serveur TCP, s'exécutant ici dans un {@link Thread}
 * 
 * Le serveur TCP est configuré avec un numéro de port (sur lequel il écoute),
 * et avec un nombre maximum de connexions.
 * 
 */
public class TCPServer extends Thread {

	private static int MAX_CLIENTS = 10;

	/**
	 * Maximum de connexions client autorises
	 */
	private int maxConnexions;

	/**
	 * Port sur lequel le serveur TCP écoute
	 */
	private int numeroPort;

	/**
	 * Constructeur par défaut pour la classe: instancie un nouveau
	 * {@link TCPServer} initialisé avec un numéro de port
	 * 
	 * @param unNumeroPort
	 */
	public TCPServer(int unNumeroPort) {
		numeroPort = unNumeroPort;
		maxConnexions = MAX_CLIENTS;
	}

	@Override
	public String toString() {
		return "[TCP Server] on port : " + numeroPort;
	}

	@Override
	public void run() {
		int nbConnexions = 0;
		Socket clientSocket = null;
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(numeroPort);
		} catch (IOException e) {
			System.out.println("Could not listen on port: " + numeroPort + ", " + e);
			System.exit(1);
		}

		/* On autorise maxConnexions traitements */
		while (nbConnexions <= maxConnexions) {
			try {
				System.out.println(" Attente du serveur pour la communication d'un client ");
				/* Nouvelle demande de connexion */
				clientSocket = serverSocket.accept();
				/* Création d'un nouveau thread pour gérer cette connexion */
				ConnectedClientThread st = new ConnectedClientThread(clientSocket, this);
				st.start();
				nbConnexions++;
				System.out.println("Nb connexions = " + nbConnexions);

			} catch (IOException e) {
				System.out.println("Accept failed: " + serverSocket.getLocalPort() + ", " + e);
				System.exit(1);
			}
		}
		System.out.println("Déjà " + nbConnexions + " clients. Maximum autorisé atteint");

		try {
			serverSocket.close();
		} catch (IOException e) {
			System.out.println("Could not close");
		}

	}

}
