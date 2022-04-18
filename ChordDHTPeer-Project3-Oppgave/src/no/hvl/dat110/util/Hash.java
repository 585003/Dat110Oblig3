package no.hvl.dat110.util;

/**
 * project 3
 * @author tdoy
 *
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash { 
	
	private static BigInteger hashint; 
	
	public static BigInteger hashOf(String entity){		
		
		// Task: Hash a given string using MD5 and return the result as a BigInteger.
		
		
		try {
			// we use MD5 with 128 bits digest
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// compute the hash of the input 'entity'
			messageDigest.update(entity.getBytes());
			byte[] digest = messageDigest.digest();
			// convert the hash into hex format
			String hexHash = toHex(digest);
			// convert the hex into BigInteger
			hashint = new BigInteger(hexHash,16);
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashint;
	}
	
	public static BigInteger addressSize() {
		
		// Task: compute the address size of MD5
		
		// get the digest length
		int digestLength = hashint.toByteArray().length;
		// compute the number of bits = digest length * 8
		int numOfBits = digestLength*8;
		// compute the address size = 2 ^ number of bits
		BigInteger addressSize = BigInteger.valueOf(2^numOfBits);
		// return the address size
		
		return addressSize;
	}
	
	public static int bitSize() {
		
		int digestlen = 0;
		
		// find the digest length
		byte[] digest = hashint.toByteArray();
		digestlen = digest.length;
		
		return digestlen*8;
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}
	public static void main(String[] args) {
		System.out.println(hashOf("process1"));
	}
}
