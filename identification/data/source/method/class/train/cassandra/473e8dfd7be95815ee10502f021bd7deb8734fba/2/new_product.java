public static SslContext getOrCreateSslContext(EncryptionOptions options, boolean buildTruststore,
                                                   SocketType socketType) throws IOException
    {
        return getOrCreateSslContext(options, buildTruststore, socketType, OpenSsl.isAvailable());
    }