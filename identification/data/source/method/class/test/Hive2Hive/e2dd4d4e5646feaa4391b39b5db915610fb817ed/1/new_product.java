public static KeyPair generateRSAKeyPair(RSA_KEYLENGTH keyLength) {

		int strength = keyLength.value();
		BigInteger publicExp = new BigInteger("10001", 16); // Fermat F4, largest known fermat prime

		try {
			JDKKeyPairGenerator gen = new JDKKeyPairGenerator.RSA();
			RSAKeyGenParameterSpec params = new RSAKeyGenParameterSpec(strength, publicExp);
			gen.initialize(params, new SecureRandom());
			return gen.generateKeyPair();
		} catch (InvalidAlgorithmParameterException e) {
			logger.error("Exception whil RSA key pair generation:", e);
		}
		return null;

		// RSAKeyPairGenerator kpg = new RSAKeyPairGenerator();
		// KeyGenerationParameters parameters = new RSAKeyGenerationParameters(publicExp, new SecureRandom(),
		// strength, certainty);
		// kpg.init(parameters);
		//
		// AsymmetricCipherKeyPair keyPair = kpg.generateKeyPair();
		// return keyPair;
	}