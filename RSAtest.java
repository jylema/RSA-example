
// Class to test RSA encryption algorithm 

import java.util.Scanner;
import java.math.BigInteger;

public class RSAtest {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.println("This is an RSA encryption program. Type the string you want encoded below.");

		// Reads plaintext from string
		String plainTXT = new String(input.nextLine());

		// Initializes RSA encryption function
		RSA crypt = new RSA();

		// Encrypts and prints out the plaintext
		BigInteger code = crypt.encrypt(plainTXT);
		System.out.println("Your string is now encrypted as \n" + code);

		// System.out.println("It can be decrypted back to \n" +
		// crypt.decrypt(code));

		input.close();

	}
}
