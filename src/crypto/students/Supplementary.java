package crypto.students;

import java.math.BigInteger;

import org.apache.log4j.Logger;

import com.sun.corba.se.spi.ior.Identifiable;

/***
 * In this class all the candidates must implement the methods related to key
 * derivation. You can create auxiliary functions if you need it, using ONLY
 * Java standard classes.
 * 
 * @author Pablo Serrano
 */
public class Supplementary
{

	private static Logger log = Logger.getLogger(Supplementary.class);

	/***
	 * Receives a 2048 bits key and applies a word by word XOR to yield a 64 bit
	 * integer at the end.
	 * 
	 * @param key
	 *            2048 bit integer form part A1 DH Key Exchange Protocol
	 * @return A 64 bit integer
	 */
	public static BigInteger parityWordChecksum(BigInteger key)
	{
		String key_binary_str = key.toString(2);
		// divide the big integer "key" into binary string chunks
		//matrix of 64 x 32
		// 01011101...
		// 10111100...
		// ...
		// 11011110...

		//stuff it to standard 2048 bits
		while (key_binary_str.length()<2048)
		{
			key_binary_str="0"+key_binary_str;
		}
		//solve it recursively
		String result_bianry_string = foldXorTo64Bits(key_binary_str);

		return new BigInteger(result_bianry_string,2);
		
		// log.error("You must implement this function!");
		// return BigInteger.ZERO;
	}

	/***
	 * 
	 * @param key
	 *            2048 bit integer form part A1 DH Key Exchange Protocol
	 * @param p
	 *            A random 64 bit prime integer
	 * @return A 64 bit integer for use as a key for a Stream Cipher
	 */
	public static BigInteger deriveSuppementaryKey(BigInteger key, BigInteger p)
	{
		return key.mod(p);
		// log.error("You must implement this function!");
		// return BigInteger.ZERO;
	}

	private static String xorBinaryString(String str1, String str2)
	{
		StringBuffer str_buf = new StringBuffer("");
		if (str1.length() != str2.length())
		{
			log.error("Two strings lengthes do not match!!!");
			return "";
		}

		else
		{
			for (int i = 0; i < str1.length(); i++)
			{
				char ch1 = str1.charAt(i);
				char ch2 = str2.charAt(i);

				if (!(ch1 == '0' || ch1 == '1') || !(ch2 == '0' || ch2 == '1'))
				{
					log.error("Illegal character!!!");
					return "";
				}

				if (ch1 == ch2)
				{
					str_buf.append('0');
				} else
				{
					str_buf.append('1');
				}
			}
			return str_buf.toString();
		}
	}

	private static String foldXorTo64Bits(String str)
	{
		if (str.length() <= 64)
			return str;
		else
		{
			String left_half = str.substring(0, str.length() / 2 );
			String right_half = str.substring(str.length() / 2);
			return xorBinaryString(foldXorTo64Bits(left_half), foldXorTo64Bits(right_half));
		}
	}
}
