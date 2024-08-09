public static SecretKey generateAESKeyFromPassword(UserPassword upw, AES_KEYLENGTH keyLength)
			throws InvalidKeySpecException {

		byte[] secretKeyEncoded = getPBKDF2Hash(upw.getPassword(), upw.getSalt(), keyLength.value());

		return new SecretKeySpec(secretKeyEncoded, "AES");
	}