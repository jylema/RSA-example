import java.net.*;
import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

public class RSAmessageClient {

	private static BigInteger rsaEncrypt(String plainTXT, BigInteger e, BigInteger modulus) {

		// Turns the plaintext into a number to be encrypted
		BigInteger txt = new BigInteger(plainTXT.getBytes());

		return txt.modPow(e, modulus);

	}

	public static void main(String[] args) throws IOException {

		if (args.length != 2) {
			System.err.println("Error: java RSAmessageClient <host_name> <port_num> ");
			System.exit(1);
		}

		String host_name = args[0];
		int port_num = Integer.parseInt(args[1]);

		try {

			// Initializes a client socket, connecting it to a server
			Socket clnt = new Socket(host_name, port_num);

			// Initializes objects for writing to and reading from socket
			ObjectOutputStream out = new ObjectOutputStream(clnt.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(clnt.getInputStream());

			BigInteger PUBLIC_KEY_MODULUS = (BigInteger) in.readObject();
			BigInteger PUBLIC_KEY_EXPONENT = (BigInteger) in.readObject();

			System.out
					.println("This is an RSA encryption-using messaging program. Type the string you want sent below");

			// Reads plaintext from input
			Scanner input = new Scanner(System.in);

			String plainTXT = new String(input.nextLine());

			BigInteger CODE = rsaEncrypt(plainTXT, PUBLIC_KEY_EXPONENT, PUBLIC_KEY_MODULUS);

			// Sends code to RSAmessageServer
			out.writeObject(CODE);

			clnt.close();
			input.close();

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
