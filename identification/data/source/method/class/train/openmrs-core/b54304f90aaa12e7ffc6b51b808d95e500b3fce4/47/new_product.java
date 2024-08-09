public static boolean hashMatches(String hashedPassword, String passwordToHash) {
		if (hashedPassword == null || passwordToHash == null) {
			throw new APIException("Neither the hashed password or the password to hash cannot be null");
		}
		
		return hashedPassword.equals(encodeString(passwordToHash))
		        || hashedPassword.equals(encodeStringSHA1(passwordToHash))
		        || hashedPassword.equals(incorrectlyEncodeString(passwordToHash));
	}