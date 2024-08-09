public static byte[] decryptDES(byte[] data, byte[] key, String transformation) {
        try {
            SecretKey secretKey = keyGenerator(key);
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }