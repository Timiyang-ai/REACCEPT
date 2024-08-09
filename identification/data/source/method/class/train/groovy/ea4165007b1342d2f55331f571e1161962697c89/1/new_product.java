public static String md5(byte[] self) throws NoSuchAlgorithmException {
        return digest(self, MD5);
    }