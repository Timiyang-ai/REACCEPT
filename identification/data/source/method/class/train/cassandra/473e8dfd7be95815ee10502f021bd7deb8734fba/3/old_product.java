@VisibleForTesting
    static SslContext getSslContext(EncryptionOptions options, boolean buildTruststore, ConnectionType connectionType,
                                    SocketType socketType, boolean useOpenSsl) throws IOException
    {
        CacheKey key = new CacheKey(options, connectionType, socketType);
        SslContext sslContext;

        sslContext = cachedSslContexts.get(key);
        if (sslContext != null)
            return sslContext;

        sslContext = createNettySslContext(options, buildTruststore, connectionType, socketType, useOpenSsl);
        SslContext previous = cachedSslContexts.putIfAbsent(key, sslContext);
        if (previous == null)
            return sslContext;

        ReferenceCountUtil.release(sslContext);
        return previous;
    }