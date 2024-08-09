public static SecretKey generateAESKeyFromPassword(UserPassword upw, AES_KEYLENGTH keyLength) {

		// generate a fixed salt out of the PIN itself
		byte[] pinEnlargementSalt = generateFixedSalt(EncryptionUtil.serializeObject(upw.getPin()));

		// enlarge PIN with enlargement salt, such that PIN has same size as the hash
		byte[] enlargedPin = getPBKDF2Hash(upw.getPin(), pinEnlargementSalt, SALT_BIT_SIZE);

		// use the enlarged PIN as salt to generate the symmetric AES key
		byte[] secretKeyEncoded = getPBKDF2Hash(upw.getPassword(), enlargedPin, keyLength.value());

		return new SecretKeySpec(secretKeyEncoded, "AES");
	}