public static org.bouncycastle.asn1.sec.ECPrivateKey convertToECPrivateKeyStructure(ECPrivateKey ecPrivateKey) {
		ECParameterSpec ecSpec = ecPrivateKey.getParams();
		BigInteger s = ecPrivateKey.getS();
		int orderBitLength = ecSpec.getOrder().bitLength();

		X962Parameters params = null;
		if (ecSpec instanceof ECNamedCurveSpec) {
			ASN1ObjectIdentifier curveOid = ECUtil.getNamedCurveOid(((ECNamedCurveSpec) ecSpec).getName());
			if (curveOid == null) {
				curveOid = new ASN1ObjectIdentifier(((ECNamedCurveSpec) ecSpec).getName());
			}
			params = new X962Parameters(curveOid);
		} else {
			ECCurve curve = EC5Util.convertCurve(ecSpec.getCurve());
			X9ECParameters ecP = new X9ECParameters(curve,
					EC5Util.convertPoint(curve, ecSpec.getGenerator(), false), ecSpec.getOrder(),
					BigInteger.valueOf(ecSpec.getCofactor()), ecSpec.getCurve().getSeed());
			params = new X962Parameters(ecP);
		}

		org.bouncycastle.asn1.sec.ECPrivateKey keyStructure =
				new org.bouncycastle.asn1.sec.ECPrivateKey(orderBitLength, s, params);
		return keyStructure;
	}