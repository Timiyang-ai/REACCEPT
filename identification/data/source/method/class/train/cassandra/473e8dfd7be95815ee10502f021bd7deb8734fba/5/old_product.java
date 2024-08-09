public static SslContext getSslContext(EncryptionOptions options, boolean buildTruststore, boolean forServer) throws IOException
    {
        return getSslContext(options, buildTruststore, forServer, OpenSsl.isAvailable());
    }