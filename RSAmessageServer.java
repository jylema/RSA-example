import java.net.*;
import java.io.*;
import java.math.BigInteger;

public class RSAmessageServer {

	public static void main(String[] args) throws IOException {

		// Checks if port number has been inputed for the server to connect to
		if (args.length != 1) {
			System.err.println("Error: java RSAmessageServer <port_num>");
			System.exit(1);
		}

		int port_num = Integer.parseInt(args[0]);

		try {

			// Creates listening socket and binds it to the port number
			@SuppressWarnings("resource")
			ServerSocket serv = new ServerSocket(port_num);

			while (true) {

				// Listens for a connection to be made to the socket and then
				// accepts it
				Socket clnt = serv.accept();

				// Initializes RSA encryption function
				RSA crypt = new RSA();

				// Initializes objects for writing to and reading from socket
				ObjectOutputStream out = new ObjectOutputStream(clnt.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(clnt.getInputStream());

				// Sends public key to MessageClient
				out.writeObject(crypt.getPublicKeyModulus());
				out.writeObject(crypt.getPublicKeyExponent());
				out.flush();

				// Reads, decrypts, and prints encrypted message from MessageClient
				System.out.println(crypt.decrypt((BigInteger) in.readObject()));

			}

		}

		catch (IOException e) {
			System.out
					.println("Exception caught during listening on port " + port_num + " or trying to make connection");
			System.out.println(e.getMessage());
		}

		catch (IllegalArgumentException e) {

			System.out.println("Exception caught due to incorrect port number " + port_num);
			System.out.println(e.getMessage());
		}

		catch (ClassNotFoundException e) {

			System.out.println("Exception caught due to incorrect object sent through socket");
			System.out.println(e.getMessage());
		}
	}

}
