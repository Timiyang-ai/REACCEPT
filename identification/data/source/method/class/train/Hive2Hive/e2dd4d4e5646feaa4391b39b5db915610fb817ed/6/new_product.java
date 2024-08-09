public static KeyPair generateRSAKeyPair(RSA_KEYLENGTH keyLength) {
		int strength = keyLength.value();
		// Fermat F4, largest known fermat prime
		BigInteger publicExp = new BigInteger("10001", 16);

		try {
			KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA", SECURITY_PROVIDER);
			RSAKeyGenParameterSpec params = new RSAKeyGenParameterSpec(strength, publicExp);
			gen.initialize(params, new SecureRandom());
			return gen.generateKeyPair();
		} catch (InvalidAlgorithmParameterException | NoSuchProviderException | NoSuchAlgorithmException e) {
			logger.error("Exception while generation of RSA key pair of length {}:", keyLength, e);
		}
		return null;
	}