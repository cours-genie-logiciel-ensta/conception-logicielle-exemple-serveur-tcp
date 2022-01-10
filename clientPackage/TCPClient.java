package clientPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Représente un client TCP : cette classe s'appuie principalement sur un objet
 * {@link Socket}, et s'initialise par un nom de serveur et un numéro de port
 */
public class TCPClient {

	private int numeroPort;
	private String nomServeur;

	private Socket socket;
	private PrintStream socOut;
	private BufferedReader socIn;

	/**
	 * Constructeur par défaut: un client se connecte a un serveur identifie par un
	 * nom (unNomServeur), sur un port unNumero
	 */
	public TCPClient(String unNomServeur, int unNumero) {
		numeroPort = unNumero;
		nomServeur = unNomServeur;
	}

	/**
	 * Cette méthode permet de se connecter au serveur TCP
	 * 
	 * @return true si la connexion a pu s'effecter, sinon false
	 */
	public boolean connectToServer() {
		boolean ok = false;
		try {
			System.out.println("Tentative : " + nomServeur + " -- " + numeroPort);
			socket = new Socket(nomServeur, numeroPort);
			socOut = new PrintStream(socket.getOutputStream());
			socIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			ok = true;
		} catch (UnknownHostException e) {
			System.err.println("Serveur inconnu : " + e);

		} catch (ConnectException e) {
			System.err.println("Exception lors de la connexion:" + e);
			e.printStackTrace();

		} catch (IOException e) {
			System.err.println("Exception lors de l'echange de donnees:" + e);
		}
		System.out.println("Connexion faite");
		return ok;
	}

	/**
	 * Cette méthode permet de se déconnecter
	 * 
	 */
	public void disconnectFromServer() {
		try {
			System.out.println("[ClientTCP] CLIENT : " + socket);
			socOut.close();
			socIn.close();
			socket.close();
		} catch (Exception e) {
			System.err.println("Exception lors de la deconnexion :  " + e);
		}
	}

	public String stringTransmitAndServerConnection(String uneChaine) {
		// String msgServeur = null;
		String chaineRetour = "";
		System.out.println("\nClient connexionTransmettreChaine " + uneChaine);

		if (connectToServer() == true) {
			try {
				socOut.println(uneChaine);
				socOut.flush();

				// code si serveur répond
				// msgServeur = socIn.readLine();
				// while (msgServeur != null && msgServeur.length() > 0) {
				// chaineRetour += msgServeur + "\n";
				// msgServeur = socIn.readLine();
				// }
				// System.out.println("Client msgServeur " + chaineRetour);

				disconnectFromServer();
			} catch (Exception e) {
				System.err.println("Exception lors de la connexion client:  " + e);
			}
		} else {
			System.err.println("Connexion echouee");
		}
		return chaineRetour;
	}

}
