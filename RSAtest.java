
// Class to test RSA encryption algorithm 

import java.util.Scanner;
import java.math.BigInteger;
import java.util.HashMap;

public class RSAtest {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.println("This is an RSA encryption program. Type the string you want encoded below.");

		// Reads plaintext from string -> byte array -> BigInteger

		BigInteger plainTXT = new BigInteger(input.nextLine().getBytes());

		/*
		 * RSA crypt = new RSA(); HashMap keys = crypt.encrypt(plainTXT);
		 * System.out.print("Encrypted code: " + keys.get(code));
		 * System.out.println("It decrypts to " + crypt.decrypt(keys));
		 */

		String completedTXT = new String(plainTXT.toByteArray());

		System.out.println("The string as a number is" + plainTXT + "and as a string is" + completedTXT);

		input.close();

	}
}
