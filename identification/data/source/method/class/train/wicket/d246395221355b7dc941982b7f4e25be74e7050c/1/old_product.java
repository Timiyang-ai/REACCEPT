@Override
	protected final byte[] crypt(final byte[] input, final int mode)
		throws GeneralSecurityException
	{
		SecretKey key = generateSecretKey();
		PBEParameterSpec spec = new PBEParameterSpec(salt, COUNT);
		Cipher ciph = Cipher.getInstance(CRYPT_METHOD);
		ciph.init(mode, key, spec);
		return ciph.doFinal(input);
	}