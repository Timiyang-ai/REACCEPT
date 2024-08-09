	@Test
	public void generateAESKeyTest() {

		// test all key sizes
		for (AES_KEYLENGTH keylength : AES_KEYLENGTH.values()) {

			logger.debug("Testing AES {}-bit key generation.", keylength.value());

			// generate AES key
			long start = System.currentTimeMillis();
			SecretKey aesKey = EncryptionUtil.generateAESKey(keylength, SECURITY_PROVIDER);
			long stop = System.currentTimeMillis();
			logger.debug("Generated AES key: {}.", EncryptionUtil.byteToHex(aesKey.getEncoded()));
			logger.debug("Time: {} ms.", stop - start);

			assertNotNull(aesKey);
			assertTrue(aesKey.getAlgorithm().equals("AES"));
		}
	}