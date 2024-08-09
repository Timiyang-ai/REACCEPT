static OpenSSLKey fromPublicKeyPemInputStream(InputStream is)
            throws InvalidKeyException {
        OpenSSLBIOInputStream bis = new OpenSSLBIOInputStream(is, true);
        try {
            long keyCtx = NativeCrypto.PEM_read_bio_PUBKEY(bis.getBioContext());
            if (keyCtx == 0L) {
                return null;
            }

            return new OpenSSLKey(keyCtx);
        } catch (Exception e) {
            throw new InvalidKeyException(e);
        } finally {
            bis.release();
        }
    }