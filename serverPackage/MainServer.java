package serverPackage;

/**
 * C'est la classe qui contient la méthode main() qui permet de lancer le
 * programme du serveur
 *
 */
public class MainServer {

	/**
	 * Methode main() qui permet de lancer le programme du serveur
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TCPServer aServer = new TCPServer(6666);

		aServer.start();

	}
}
