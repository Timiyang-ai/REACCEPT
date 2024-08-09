public static boolean hashMatches(String hashedPassword, String passwordToHash) {
		if (hashedPassword == null || passwordToHash == null)
			throw new APIException("Neither the hashed password or the password to hash cannot be null");
		
		String newHashedPassword = encodeString(passwordToHash);
		if (hashedPassword.equals(newHashedPassword))
			return true;
		else {
			String incorrectlyHashedPassword = incorrectlyEncodeString(passwordToHash);
			return hashedPassword.equals(incorrectlyHashedPassword);
		}
	}