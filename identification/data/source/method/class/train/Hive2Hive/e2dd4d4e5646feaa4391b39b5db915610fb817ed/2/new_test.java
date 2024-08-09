	@Test
	public void generateRSAKeyPairTest() {

		// test all key sizes
		for (RSA_KEYLENGTH keyLength : RSA_KEYLENGTH.values()) {
			logger.debug("Testing RSA {}-bit key pair generation.", keyLength.value());

			// generate RSA key pair
			long start = System.currentTimeMillis();
			KeyPair rsaKeyPair = generateRSAKeyPair(keyLength);
			long stop = System.currentTimeMillis();

			assertNotNull(rsaKeyPair);
			assertNotNull(rsaKeyPair.getPrivate());
			assertNotNull(rsaKeyPair.getPublic());

			logger.debug("Private Key: {}.", EncryptionUtil.byteToHex(rsaKeyPair.getPrivate().getEncoded()));
			logger.debug("Public Key: {}.", EncryptionUtil.byteToHex(rsaKeyPair.getPublic().getEncoded()));
			logger.debug("Time: {} ms.", stop - start);
		}
	}