public static ECKey fromPrivate(BigInteger privKey) {
        return new ECKey(privKey, compressPoint(CURVE.getG().multiply(privKey)));
    }