	@Test
	public void generateSaltTest() {

		byte[][] salt = new byte[100][];
		for (int i = 0; i < salt.length; i++) {

			// test salt generation
			salt[i] = PasswordUtil.generateRandomSalt();
			assertNotNull(salt[i]);
			assertTrue(salt[i].length == PasswordUtil.SALT_BIT_SIZE / 8);

			logger.debug("Generated Salt: {}.", EncryptionUtil.byteToHex(salt[i]));

			// test whether salts are random
			for (int j = 0; j < i; j++) {
				assertFalse(Arrays.equals(salt[i], salt[j]));
			}
		}
	}