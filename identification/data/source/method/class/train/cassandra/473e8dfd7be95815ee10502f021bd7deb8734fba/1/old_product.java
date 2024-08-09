public static SslContext getSslContext(EncryptionOptions options, boolean buildTruststore, ConnectionType connectionType,
                                           SocketType socketType) throws IOException
    {
        return getSslContext(options, buildTruststore, connectionType, socketType, OpenSsl.isAvailable());
    }