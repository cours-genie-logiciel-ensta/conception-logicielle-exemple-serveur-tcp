package clientPackage;

import java.util.Scanner;

/**
 * C'est la classe qui contient la méthode main() qui permet de lancer le
 * programme du client
 *
 */
public class MainClient {

	/**
	 * Methode main() qui permet de lancer le programme du client
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String myS = null;
		Scanner aSC = new Scanner(System.in);
		TCPClient myClient = new TCPClient("localhost", 6666);

		for (int i = 0; i < 5; i++) {
			System.out.println(" Saisir une chaine ");
			myS = aSC.nextLine();

			myClient.stringTransmitAndServerConnection(myS);

		}

		aSC.close();
	}
}
