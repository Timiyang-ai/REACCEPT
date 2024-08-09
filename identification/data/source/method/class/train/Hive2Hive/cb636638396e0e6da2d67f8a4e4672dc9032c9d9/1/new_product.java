public static SecretKey generateAESKeyFromPassword(String password, String pin, AES_KEYLENGTH keyLength) {

		// generate a fixed salt out of the PIN itself
		byte[] pinEnlargementSalt = generateFixedSalt(pin.getBytes());

		// enlarge PIN with enlargement salt, such that PIN has same size as the hash
		byte[] enlargedPin = getPBKDF2Hash(pin.toCharArray(), pinEnlargementSalt, SALT_BIT_SIZE);

		// use the enlarged PIN as salt to generate the symmetric AES key
		byte[] secretKeyEncoded = getPBKDF2Hash(password.toCharArray(), enlargedPin,
				keyLength.value());

		return new SecretKeySpec(secretKeyEncoded, "AES");
	}