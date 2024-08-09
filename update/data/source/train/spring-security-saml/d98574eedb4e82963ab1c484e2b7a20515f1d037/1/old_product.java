private void initialize(Resource storeFile, String storePass, String storeType) {
        InputStream inputStream = null;
        try {
            inputStream = storeFile.getInputStream();
            ks = KeyStore.getInstance(storeType);
            ks.load(inputStream, storePass.toCharArray());
        } catch (Exception e) {
            log.error("Error initializing key store", e);
            throw new RuntimeException("Error initializing keystore", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.debug("Error closing input stream for keystore.", e);
                }
            }
        }
    }