public static byte[] generateHash(char[] password, byte[] salt) throws InvalidKeySpecException {

		// hash the password
		return pbkdf2(password, salt);
	}