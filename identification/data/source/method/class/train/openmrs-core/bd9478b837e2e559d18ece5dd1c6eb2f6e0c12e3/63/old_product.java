public static String encodeString(String strToEncode) throws APIException {
		String algorithm = "SHA1";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(algorithm);
		}
		catch (NoSuchAlgorithmException e) {
			// Yikes! Can't encode password...what to do?
			log.error("Can't encode password because the given algorithm: " + algorithm + "was not found! (fail)", e);
			throw new APIException("System cannot find password encryption algorithm");
		}
		byte[] input = strToEncode.getBytes(); //TODO: pick a specific character encoding, don't rely on the platform default
		return hexString(md.digest(input));
	}