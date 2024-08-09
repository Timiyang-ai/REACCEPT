public static boolean validatePassword(char[] password, byte[] salt, byte[] correctHash)
			throws InvalidKeySpecException {

		// compute hash of password using same salt, iteration count and hash length
		byte[] testHash = pbkdf2(password, salt);

		// compare the hashes in constant time
		return slowCompare(correctHash, testHash);
	}