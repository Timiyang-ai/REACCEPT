public static String encodeString(String strToEncode) throws APIException {
		String algorithm = "SHA-512";
		MessageDigest md;
		byte[] input;
		try {
			md = MessageDigest.getInstance(algorithm);
			input = strToEncode.getBytes(StandardCharsets.UTF_8);
		}
		catch (NoSuchAlgorithmException e) {
			// Yikes! Can't encode password...what to do?
			log.error(getPasswordEncodeFailMessage(algorithm), e);
			throw new APIException("system.cannot.find.password.encryption.algorithm", null, e);
		}

		return hexString(md.digest(input));
	}