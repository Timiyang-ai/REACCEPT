@VisibleForTesting
    static SslContext getSslContext(EncryptionOptions options, boolean buildTruststore, boolean forServer, boolean useOpenSsl) throws IOException
    {
        if (forServer && serverSslContext.get() != null)
            return serverSslContext.get();
        if (!forServer && clientSslContext.get() != null)
            return clientSslContext.get();

        /*
            There is a case where the netty/openssl combo might not support using KeyManagerFactory. specifically,
            I've seen this with the netty-tcnative dynamic openssl implementation. using the netty-tcnative static-boringssl
            works fine with KeyManagerFactory. If we want to support all of the netty-tcnative options, we would need
            to fall back to passing in a file reference for both a x509 and PKCS#8 private key file in PEM format (see
            {@link SslContextBuilder#forServer(File, File, String)}). However, we are not supporting that now to keep
            the config/yaml API simple.
         */
        KeyManagerFactory kmf = null;
        if (forServer || options.require_client_auth)
            kmf = buildKeyManagerFactory(options);

        SslContextBuilder builder;
        if (forServer)
        {
            builder = SslContextBuilder.forServer(kmf);
            builder.clientAuth(options.require_client_auth ? ClientAuth.REQUIRE : ClientAuth.NONE);
        }
        else
        {
            builder = SslContextBuilder.forClient().keyManager(kmf);
        }

        builder.sslProvider(useOpenSsl ? SslProvider.OPENSSL : SslProvider.JDK);

        // only set the cipher suites if the opertor has explicity configured values for it; else, use the default
        // for each ssl implemention (jdk or openssl)
        if (options.cipher_suites != null && options.cipher_suites.length > 0)
            builder.ciphers(Arrays.asList(options.cipher_suites), SupportedCipherSuiteFilter.INSTANCE);

        if (buildTruststore)
            builder.trustManager(buildTrustManagerFactory(options));

        SslContext ctx = builder.build();
        AtomicReference<SslContext> ref = forServer ? serverSslContext : clientSslContext;
        if (ref.compareAndSet(null, ctx))
            return ctx;

        ReferenceCountUtil.release(ctx);
        return ref.get();
    }