public static byte[] generateSalt() {

		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_BIT_SIZE];
		random.nextBytes(salt);
		return salt;
	}