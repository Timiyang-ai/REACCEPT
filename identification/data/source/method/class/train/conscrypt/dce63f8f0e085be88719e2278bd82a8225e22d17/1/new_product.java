static native void setLocalCertsAndPrivateKey(long ssl, NativeSsl ssl_holder, byte[][] encodedCertificates,
        NativeRef.EVP_PKEY pkey) throws SSLException;