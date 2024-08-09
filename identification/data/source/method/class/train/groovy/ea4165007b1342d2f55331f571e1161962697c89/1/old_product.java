public static String md5(byte[] self) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(ByteBuffer.wrap(self));

        return String.format("%032x", new BigInteger(1, md5.digest()));
    }