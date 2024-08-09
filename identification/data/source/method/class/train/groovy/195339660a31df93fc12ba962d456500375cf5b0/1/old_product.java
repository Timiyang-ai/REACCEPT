public static String digest(byte[] self, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(ByteBuffer.wrap(self));

        return String.format("%032x", new BigInteger(1, md.digest()));
    }