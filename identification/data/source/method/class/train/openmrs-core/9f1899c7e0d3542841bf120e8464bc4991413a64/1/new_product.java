public static String encrypt(String text, byte[] initVector, byte[] secretKey) {
		IvParameterSpec initVectorSpec = new IvParameterSpec(initVector);
		SecretKeySpec secret = new SecretKeySpec(secretKey, OpenmrsConstants.ENCRYPTION_KEY_SPEC);
		byte[] encrypted;
		String result;
		
		try {
			Cipher cipher = Cipher.getInstance(OpenmrsConstants.ENCRYPTION_CIPHER_CONFIGURATION);
			cipher.init(Cipher.ENCRYPT_MODE, secret, initVectorSpec);
			encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
			result = new String(Base64.getEncoder().encode(encrypted), StandardCharsets.UTF_8);
		}
		catch (GeneralSecurityException e) {
			throw new APIException("could.not.encrypt.text", null, e);
		}
		
		return result;
	}