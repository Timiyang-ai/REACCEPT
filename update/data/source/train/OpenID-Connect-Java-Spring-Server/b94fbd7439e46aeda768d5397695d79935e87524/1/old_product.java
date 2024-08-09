private Signature getSigner() throws NoSuchAlgorithmException{
		if(signer == null){
			signer = Signature.getInstance(JwsAlgorithm.getByName(super.getAlgorithm()).getStandardName());
		}
		return signer;
	}