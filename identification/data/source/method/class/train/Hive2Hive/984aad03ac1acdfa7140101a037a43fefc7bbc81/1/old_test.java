	@Test
	public void validatePasswordTest() {
		// test various passwords
		char[][] password = new char[10][];
		for (int i = 0; i < password.length; i++) {

			// set a random password and salt
			password[i] = randomString(20).toCharArray();
			byte[] salt = PasswordUtil.generateRandomSalt();

			logger.debug("Validating password '{}' with salt '{}'.", String.valueOf(password[i]),
					EncryptionUtil.byteToHex(salt));

			// generate hash
			byte[] hash = PasswordUtil.generateHash(password[i], salt);

			// validate password
			boolean isValid = PasswordUtil.validatePassword(password[i], salt, hash);

			assertTrue(isValid);

			// test validation with wrong password, salt or hash
			for (int j = 0; j < 3; j++) {

				// assure new parameters
				char[] otherPW;
				do {
					otherPW = randomString(20).toCharArray();
				} while (Arrays.equals(otherPW, password[i]));
				byte[] otherSalt;
				do {
					otherSalt = PasswordUtil.generateRandomSalt();
				} while (Arrays.equals(otherSalt, salt));
				byte[] otherHash = null;
				do {
					otherHash = PasswordUtil.generateHash(randomString(20).toCharArray(), PasswordUtil.generateRandomSalt());
				} while (Arrays.equals(otherHash, hash));

				assertFalse(PasswordUtil.validatePassword(otherPW, salt, hash));
				assertFalse(PasswordUtil.validatePassword(password[i], otherSalt, hash));
				assertFalse(PasswordUtil.validatePassword(password[i], salt, otherHash));

				assertFalse(PasswordUtil.validatePassword(otherPW, otherSalt, hash));
				assertFalse(PasswordUtil.validatePassword(password[i], otherSalt, otherHash));
				assertFalse(PasswordUtil.validatePassword(otherPW, salt, otherHash));

				assertFalse(PasswordUtil.validatePassword(otherPW, otherSalt, otherHash));
			}
		}
	}