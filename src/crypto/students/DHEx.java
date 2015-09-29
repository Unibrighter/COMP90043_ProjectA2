package crypto.students;

import java.math.BigInteger;
import java.util.Random;

import org.apache.log4j.Logger;


/***
 * In this class, all the candidates must implement their own math and crypto
 * functions required to solve any calculation and encryption/decryption task
 * involved in this project.
 * 
 * @author pabloserrano
 *
 */
public class DHEx
{

	// debug logger
	private static Logger log = Logger.getLogger(DHEx.class);

	private static Random rnd = new Random();

	/**
	 * @param int | The length of the big random integer
	 * 
	 * @return BigInteger | The big integer with the length of given parameter
	 *         This method won't return a prime number as a result in order to
	 *         avoid the huge calculation time
	 * */
	public static BigInteger createPrivateKey(int size)
	{

		return new BigInteger(size, 0, DHEx.rnd);

		// log.debug("You must implement this function!");
		// return BigInteger.ONE;
	}

	/**
	 * @param BigInteger
	 *            generator shared over public channel
	 * @param BigInteger
	 *            prime a giant prime number used as modulo
	 * @param BigInteger
	 *            skClient is a random number as the secret chosen key by clinet
	 * 
	 * @return BigInteger[] ,skClient as pair[0],pkClient as pair[1]
	 * */
	public static BigInteger[] createDHPair(BigInteger generator,
			BigInteger prime, BigInteger skClient)
	{
		BigInteger[] pair = new BigInteger[2];

		// log.debug("You must implement this function!");
		pair[0] = skClient;

		// g^a mod p
		pair[1] = DHEx.modExp(generator, skClient, prime);
		return pair;
	}

	/**
	 * @param BigInteger
	 *            pk is the key (B) sent over public channel from server
	 * @param BigInteger
	 *            sk is the key chosen locally (a) kept as secret key in client
	 *            side
	 * @param BigInteger
	 *            prime a giant prime number used as modulo,which has been
	 *            negotiated by two sides over public channel
	 * 
	 * @return BigInteger the result of shared key
	 * */
	public static BigInteger getDHSharedKey(BigInteger pk, BigInteger sk,
			BigInteger prime)
	{

		return DHEx.modExp(pk, sk, prime);

		// BigInteger shared = BigInteger.ZERO;
		// log.debug("You must implement this function!");
		// return shared;
	}

	/**
	 * @param BigInteger
	 *            base The base number in the modulation exponentiation formula
	 * @param BigInteger
	 *            exp The number stands for exponentiation in the formula
	 * @param BigInteger
	 *            The modulo number stands for the mod base of the formula
	 * 
	 * @return the result of the calculation
	 * */
	public static BigInteger modExp(BigInteger base, BigInteger exp,
			BigInteger modulo)
	{

		// the basic idea of this function is to reduce the complexity of
		// calculation of X^p
		// from the O(p) to O(length of p in binary format)

		// change the given exp into Binary String
		String exp_binary = exp.toString(2);

		// use the String to calculate the result of the modulation
		BigInteger result = BigInteger.ONE;

		// this loop used binary mod calculation to reduce the complexity
		// Since a^exp % m = ((a^(2^index_n_of_exp_binary)) % m) *
		// ((a^(2^index_n_of_exp_binary-1)) % m)*.... * ((a^1) % m)
		// And we know that (a%m)^2 % m = a^2 % m
		// combine this two together we can simply write the algorithm in the
		// way below

		for (int i = exp_binary.length() - 1; i >= 0; i--)
		{

			if (exp_binary.charAt(i) == '1')
			{
				result = result.multiply(base).mod(modulo);
			}
			base = base.pow(2).mod(modulo);
		}

		return result;

		// log.debug("You must implement this function!");
		// return BigInteger.ZERO;
	}

	public static void debugTest()
	{
		System.out.println("p=23 g=5");

		System.out.println("Alice choose a=6 ");

		System.out.println("A=5^6 mod 23");

		System.out.println(DHEx.modExp(new BigInteger("5"),
				new BigInteger("6"), new BigInteger("23")));
	}

}
