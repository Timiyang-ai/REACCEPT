public static byte[] encryptDES(byte[] data, byte[] key, String transformation) {
        try {
            SecretKey secretKey = keyGenerator(key);
            Cipher cipher = Cipher.getInstance(transformation);
            SecureRandom random = new SecureRandom();
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
            return cipher.doFinal(data);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }