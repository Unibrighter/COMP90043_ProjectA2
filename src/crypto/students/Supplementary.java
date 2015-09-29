package crypto.students;

import java.math.BigInteger;

import org.apache.log4j.Logger;

/***
 * In this class all the candidates must implement the methods
 * related to key derivation. You can create auxiliary functions
 * if you need it, using ONLY Java standard classes.
 * 
 * @author Pablo Serrano
 */
public class Supplementary {
	
	private static Logger log = Logger.getLogger(Supplementary.class);
	
	/***
	 * Receives a 2048 bits key and applies a word by word XOR
	 * to yield a 64 bit integer at the end.
	 * 
	 * @param key 2048 bit integer form part A1 DH Key Exchange Protocol
	 * @return A 64 bit integer
	 */
	public static BigInteger parityWordChecksum(BigInteger key) {
		log.error("You must implement this function!");
		return BigInteger.ZERO;
	}

	/***
	 * 
	 * @param key 2048 bit integer form part A1 DH Key Exchange Protocol
	 * @param p A random 64 bit prime integer
	 * @return A 64 bit integer for use as a key for a Stream Cipher
	 */
	public static BigInteger deriveSuppementaryKey(BigInteger key, BigInteger p) {
		log.error("You must implement this function!");
		return BigInteger.ZERO;
	}
}
