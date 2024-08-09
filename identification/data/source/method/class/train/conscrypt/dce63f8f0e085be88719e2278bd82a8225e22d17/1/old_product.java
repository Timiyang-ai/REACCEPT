static native void setLocalCertsAndPrivateKey(long ssl, byte[][] encodedCertificates,
        NativeRef.EVP_PKEY pkey) throws SSLException;