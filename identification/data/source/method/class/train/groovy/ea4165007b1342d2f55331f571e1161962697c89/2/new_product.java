public static String md5(CharSequence self) throws NoSuchAlgorithmException {
        return digest(self, MD5);
    }