public static String decrypt(String text, byte[] initVector, byte[] secretKey) {
		IvParameterSpec initVectorSpec = new IvParameterSpec(initVector);
		SecretKeySpec secret = new SecretKeySpec(secretKey, OpenmrsConstants.ENCRYPTION_KEY_SPEC);
		String decrypted = null;
		
		try {
			Cipher cipher = Cipher.getInstance(OpenmrsConstants.ENCRYPTION_CIPHER_CONFIGURATION);
			cipher.init(Cipher.DECRYPT_MODE, secret, initVectorSpec);
			byte[] original = cipher.doFinal(Base64.decode(text));
			decrypted = new String(original, encoding);
		}
		catch (GeneralSecurityException e) {
			throw new APIException("could.not.decrypt.text", null, e);
		}
		catch (UnsupportedEncodingException e) {
			throw new APIException("system.cannot.find.encoding", new Object[] { encoding }, e);
		}
		
		return decrypted;
	}