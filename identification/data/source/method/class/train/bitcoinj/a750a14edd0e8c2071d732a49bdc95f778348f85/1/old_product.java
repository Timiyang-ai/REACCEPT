public static ECKey fromPrivate(BigInteger privKey, boolean compressed) {
        ECPoint point = CURVE.getG().multiply(privKey);
        return new ECKey(privKey, compressed ? compressPoint(point) : decompressPoint(point));
    }