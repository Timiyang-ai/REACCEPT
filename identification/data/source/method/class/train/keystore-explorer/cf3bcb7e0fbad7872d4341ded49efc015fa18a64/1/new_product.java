public static org.bouncycastle.asn1.sec.ECPrivateKey convertToECPrivateKeyStructure(ECPrivateKey ecPrivateKey)
			throws IOException {
		byte[] encoded = ecPrivateKey.getEncoded();
		PrivateKeyInfo privateKeyInfo = PrivateKeyInfo.getInstance(encoded);
		ASN1Encodable privateKey = privateKeyInfo.parsePrivateKey();
		return org.bouncycastle.asn1.sec.ECPrivateKey.getInstance(privateKey);
	}