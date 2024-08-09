public static byte[] generateHash(char[] password, byte[] salt) {

		// hash the password
		return getPBKDF2Hash(password, salt, HASH_BIT_SIZE);
	}