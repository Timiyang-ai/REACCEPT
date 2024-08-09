public void initializeSigner() throws NoSuchAlgorithmException{
		signer = Signature.getInstance(JwsAlgorithm.getByName(super.getAlgorithm()).getStandardName());
	}