	@Test
	public void generateHashTest() {
		// test various passwords
		char[][] password = new char[5][];
		for (int i = 0; i < password.length; i++) {

			// set a random password and salt
			password[i] = randomString(20).toCharArray();
			byte[] salt = PasswordUtil.generateRandomSalt();

			logger.debug("Tested Password: {}.", String.valueOf(password[i]));

			// test hash generation
			byte[] hash = PasswordUtil.generateHash(password[i], salt);

			assertNotNull(hash);
			assertTrue(hash.length == PasswordUtil.HASH_BIT_SIZE / 8);

			logger.debug("Generated Salt: {}.", EncryptionUtil.byteToHex(hash));

			// test if hash outcome stays always the same with the same password and salt
			for (int j = 0; j < 5; j++) {
				assertTrue(Arrays.equals(hash, PasswordUtil.generateHash(password[i], salt)));
			}

			// test if hash outcome changes with other password or salt
			for (int j = 0; j < 5; j++) {

				// assure new parameters
				char[] otherPW;
				do {
					otherPW = randomString(20).toCharArray();
				} while (Arrays.equals(otherPW, password[i]));
				byte[] otherSalt;
				do {
					otherSalt = PasswordUtil.generateRandomSalt();
				} while (Arrays.equals(otherSalt, salt));

				assertFalse(Arrays.equals(hash, PasswordUtil.generateHash(password[i], otherSalt)));
				assertFalse(Arrays.equals(hash, PasswordUtil.generateHash(otherPW, salt)));
				assertFalse(Arrays.equals(hash, PasswordUtil.generateHash(otherPW, otherSalt)));
			}
		}
	}