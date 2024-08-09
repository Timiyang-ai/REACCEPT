public void init(PrivateKey key) {
        try {
            if (signature != null) {
                signature.initSign(key);
            } else if (cipher != null) {
                cipher.init(Cipher.ENCRYPT_MODE, key);
            }
        } catch (InvalidKeyException e){
            throw new AlertException(AlertProtocol.BAD_CERTIFICATE,
                    new SSLException("init - invalid private key", e));
        }
    }