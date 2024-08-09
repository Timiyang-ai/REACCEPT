public static SslContext getSslContext(EncryptionOptions options, boolean buildTruststore,
                                           SocketType socketType) throws IOException
    {
        return getSslContext(options, buildTruststore, socketType, OpenSsl.isAvailable());
    }