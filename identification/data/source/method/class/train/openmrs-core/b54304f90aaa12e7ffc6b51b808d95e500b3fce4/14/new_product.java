public static boolean hashMatches(String hashedPassword, String passwordToHash) {
		if (hashedPassword == null || passwordToHash == null) {
			throw new APIException("password.cannot.be.null", (Object[]) null);
		}
		
		return hashedPassword.equals(encodeString(passwordToHash))
		        || hashedPassword.equals(encodeStringSHA1(passwordToHash))
		        || hashedPassword.equals(incorrectlyEncodeString(passwordToHash));
	}