import java.math.BigInteger;
import java.util.Random;

// Class to implement RSA encryption algorithm

public class RSA {

	private BigInteger p;
	private BigInteger q;
	public BigInteger modulus;
	private BigInteger totient;
	public BigInteger e;
	private BigInteger d;

	public RSA() {

		// Produces two large prime numbers to be used in the encryption
		// function
		p = this.lgPrimeGenerator();
		q = this.lgPrimeGenerator();
		// Verifies the primes are different
		while (p.equals(q)) {
			q = this.lgPrimeGenerator();
		}

		// Creates modulus by multiplying the two primes
		modulus = p.multiply(q);

		// Creates totient by multiplying both primes subtracted by 1
		totient = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

		// Initializes public key
		e = new BigInteger("65537");
		// Verifies public key is not a factor of the totient
		while (totient.mod(e).equals(BigInteger.ZERO)) {
			e = e.nextProbablePrime();
		}

		// Initializes private key
		d = e.modInverse(totient);

	}

	private BigInteger lgPrimeGenerator() {
		// Creates a (pseudo)-random large integer which is probably prime - <=
		// 2^100
		// chance of being composite
		Random rnd = new Random(System.nanoTime());
		return BigInteger.probablePrime(1024, rnd);

	}

	public BigInteger encrypt(String plainTXT) {

		// Turns the plaintext into a number to be encrypted
		BigInteger txt = new BigInteger(plainTXT.getBytes());

		// Puts the number through the generated encryption function
		return txt.modPow(e, modulus);
	}

	public String decrypt(BigInteger code) {

		BigInteger txt = code.modPow(d, modulus);
		return new String(txt.toByteArray());
	}

	public BigInteger getPublicKeyModulus() {

		return modulus;
	}

	public BigInteger getPublicKeyExponent() {

		return e;
	}

}
