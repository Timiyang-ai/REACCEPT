public static byte[] encryptMD5(byte[] data) {
        if (data.length > 0) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(data);
                return md.digest();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }