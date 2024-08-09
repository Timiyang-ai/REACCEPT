public void init(PrivateKey key) {
        try {
            if (signature != null) {
                signature.initSign(key);
            } else if (cipher != null) {
                cipher.init(Cipher.ENCRYPT_MODE, key);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }