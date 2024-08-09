public static KeyPair generateRSAKeyPair(RSA_KEYLENGTH keyLength) {
		try {
			KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA", "BC");
			RSAKeyGenParameterSpec params = new RSAKeyGenParameterSpec(keyLength.value(), RSA_PUBLIC_EXP);
			gen.initialize(params, new SecureRandom());
			return gen.generateKeyPair();
		} catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
			logger.error("Exception while generation of RSA key pair of length {}:", keyLength, e);
		}
		return null;
	}