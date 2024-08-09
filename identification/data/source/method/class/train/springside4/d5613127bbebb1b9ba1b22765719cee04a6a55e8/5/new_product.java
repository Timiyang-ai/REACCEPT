private static byte[] aes(byte[] input, byte[] key, int mode) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, AES_ALG);
			Cipher cipher = Cipher.getInstance(AES_ALG);
			cipher.init(mode, secretKey);
			return cipher.doFinal(input);
		} catch (GeneralSecurityException e) {
			throw ExceptionUtil.unchecked(e);
		}
	}