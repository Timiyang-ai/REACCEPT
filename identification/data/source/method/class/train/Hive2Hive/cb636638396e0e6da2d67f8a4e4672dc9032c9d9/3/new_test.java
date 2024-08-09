	@Test
	public void generateAESKeyFromPasswordTest() {

		// test all key sizes
		for (AES_KEYLENGTH keyLength : AES_KEYLENGTH.values()) {

			// test various UserPasswords
			for (int i = 0; i < 3; i++) {

				String randomPW = randomString(20);
				String randomPIN = randomString(6);

				logger.debug("Testing {}-bit AES key generation from user password and PIN:", keyLength.value());
				logger.debug("Random PW: {}.", randomPW);
				logger.debug("Random PIN: {}.", randomPIN);

				// test the generation process multiple times to ensure consistent result
				SecretKey[] aesKey = new SecretKey[3];
				for (int j = 0; j < aesKey.length; j++) {

					// generate AES key
					aesKey[j] = PasswordUtil.generateAESKeyFromPassword(randomPW, randomPIN, keyLength);

					assertNotNull(aesKey[j]);
					assertNotNull(aesKey[j].getEncoded());
					assertTrue(aesKey[j].getEncoded().length == keyLength.value() / 8);

					logger.debug("Generated {}-bit AES key: {}.", keyLength.value(),
							EncryptionUtil.byteToHex(aesKey[j].getEncoded()));

					// test whether generated AES passwords are equal
					for (int k = 0; k < j; k++) {
						assertTrue(Arrays.equals(aesKey[k].getEncoded(), aesKey[j].getEncoded()));
					}
				}
			}

		}
	}