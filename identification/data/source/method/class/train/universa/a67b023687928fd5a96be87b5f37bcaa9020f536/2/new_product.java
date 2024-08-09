public byte[] etaEncrypt(byte[] data) throws EncryptionError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            final EtaEncryptingStream s = etaEncryptStream(bos);
            s.write(data);
            s.end();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("unexpected IOError", e);
        }
    }