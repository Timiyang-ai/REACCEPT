public static byte[] generateRandomSalt() {

		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_BIT_SIZE / 8];
		random.nextBytes(salt);
		return salt;
	}