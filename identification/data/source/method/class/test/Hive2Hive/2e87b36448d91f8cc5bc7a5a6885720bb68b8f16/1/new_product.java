public static SecretKey generateAESKey(AES_KEYLENGTH keyLength, String securityProvider) {
		try {
			final KeyGenerator kg = KeyGenerator.getInstance("AES", securityProvider);
			kg.init(keyLength.value(), new SecureRandom());
			byte[] encoded = kg.generateKey().getEncoded();
			return new SecretKeySpec(encoded, "AES");
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			logger.error("Exception while AES key generator instance creation:", e);
		}
		return null;

	}