public static String encodeString(String strToEncode) throws APIException {
		String algorithm = "SHA-512";
		MessageDigest md;
		byte[] input;
		try {
			md = MessageDigest.getInstance(algorithm);
			input = strToEncode.getBytes(encoding);
		}
		catch (NoSuchAlgorithmException e) {
			// Yikes! Can't encode password...what to do?
			log.error("Can't encode password because the given algorithm: " + algorithm + "was not found! (fail)", e);
			throw new APIException("System cannot find password encryption algorithm", e);
		}
		catch (UnsupportedEncodingException e) {
			throw new APIException("System cannot find " + encoding + " encoding", e);
		}
		return hexString(md.digest(input));
	}