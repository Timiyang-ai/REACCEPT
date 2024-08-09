public static String digest(byte[] self, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance(algorithm);
        md5.update(ByteBuffer.wrap(self));

        return String.format("%032x", new BigInteger(1, md5.digest()));
    }