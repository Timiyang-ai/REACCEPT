@Override
	protected byte[] crypt(final byte[] input, final int mode)
		throws GeneralSecurityException
	{
		SecretKey key = generateSecretKey();
		AlgorithmParameterSpec spec = createParameterSpec();
		Cipher ciph = createCipher(key, spec, mode);
		return ciph.doFinal(input);
	}