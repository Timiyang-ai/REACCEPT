public static KeyPair generateRSAKeyPair(RSA_KEYLENGTH keyLength) {
		// installBCProvider();

		int strength = keyLength.value();
		// Fermat F4, largest known fermat prime
		BigInteger publicExp = new BigInteger("10001", 16);

		try {
			// TODO Use default key generator until this bug is fixed:
			// https://github.com/RuedigerMoeller/fast-serialization/issues/52
			KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA"/* , "BC */);
			RSAKeyGenParameterSpec params = new RSAKeyGenParameterSpec(strength, publicExp);
			gen.initialize(params, new SecureRandom());
			return gen.generateKeyPair();
		} catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException e) {
			logger.error("Exception while generation of RSA key pair of length {}:", keyLength, e);
		}
		return null;
	}