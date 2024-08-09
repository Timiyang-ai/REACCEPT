public static byte[] generateHash(char[] password, byte[] salt) throws InvalidKeySpecException {

		// hash the password
		return getPBKDF2Hash(password, salt, HASH_BIT_SIZE);
	}