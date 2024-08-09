public static String encrypt(String text, byte[] initVector, byte[] secretKey) {
		IvParameterSpec initVectorSpec = new IvParameterSpec(initVector);
		SecretKeySpec secret = new SecretKeySpec(secretKey, OpenmrsConstants.ENCRYPTION_KEY_SPEC);
		byte[] encrypted;
		
		try {
			Cipher cipher = Cipher.getInstance(OpenmrsConstants.ENCRYPTION_CIPHER_CONFIGURATION);
			cipher.init(Cipher.ENCRYPT_MODE, secret, initVectorSpec);
			encrypted = cipher.doFinal(text.getBytes(encoding));
		}
		catch (GeneralSecurityException e) {
			throw new APIException("could not encrypt text", e);
		}
		catch (UnsupportedEncodingException e) {
			throw new APIException("System cannot find " + encoding + " encoding", e);
		}
		
		return Base64.encode(encrypted);
	}