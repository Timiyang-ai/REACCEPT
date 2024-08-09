@VisibleForTesting
    static SslContext getOrCreateSslContext(EncryptionOptions options, boolean buildTruststore,
                                            SocketType socketType, boolean useOpenSsl) throws IOException
    {
        CacheKey key = new CacheKey(options, socketType);
        SslContext sslContext;

        sslContext = cachedSslContexts.get(key);
        if (sslContext != null)
            return sslContext;

        sslContext = createNettySslContext(options, buildTruststore, socketType, useOpenSsl);

        SslContext previous = cachedSslContexts.putIfAbsent(key, sslContext);
        if (previous == null)
            return sslContext;

        ReferenceCountUtil.release(sslContext);
        return previous;
    }