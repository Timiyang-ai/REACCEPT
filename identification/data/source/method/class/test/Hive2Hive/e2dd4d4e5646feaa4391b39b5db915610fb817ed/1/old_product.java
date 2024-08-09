public static KeyPair generateRSAKeyPair(RSA_KEYLENGTH keyLength) {
		KeyPairGenerator gen = new JDKKeyPairGenerator.RSA();
		return gen.generateKeyPair();

		// BigInteger publicExp = new BigInteger("10001", 16); // Fermat F4, largest known fermat prime
		// int strength = keyLength.value();
		// int certainty = 80; // certainty for the numbers to be primes, values >80 slow down algorithm

		// RSAKeyPairGenerator kpg = new RSAKeyPairGenerator();
		// KeyGenerationParameters parameters = new RSAKeyGenerationParameters(publicExp, new SecureRandom(),
		// strength, certainty);
		// kpg.init(parameters);
		//
		// AsymmetricCipherKeyPair keyPair = kpg.generateKeyPair();
		// return keyPair;
	}