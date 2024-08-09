public static boolean validatePassword(char[] password, byte[] salt, byte[] correctHash) {

		// compute hash of password using same salt, iteration count and hash length
		byte[] testHash = getPBKDF2Hash(password, salt, HASH_BIT_SIZE);

		// compare the hashes in constant time
		return slowCompare(correctHash, testHash);
	}