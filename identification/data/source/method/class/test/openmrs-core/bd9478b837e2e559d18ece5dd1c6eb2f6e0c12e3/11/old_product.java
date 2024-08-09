public static String encodeString(String strToEncode) throws ContextAuthenticationException {
    	MessageDigest md;
    	try {
    		md = MessageDigest.getInstance("SHA1");
    	}
    	catch (NoSuchAlgorithmException e) {
			// Yikes! Can't encode password...what to do?
    		log.error(e);
			throw new ContextAuthenticationException("System cannot find password encryption algorithm");
    	}
		byte[] input = strToEncode.getBytes(); //TODO: pick a specific character encoding, don't rely on the platform default
		return hexString(md.digest(input));
    }