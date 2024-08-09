private static SSLEngineFactory createSSLEngineFactory(
			final Configuration config,
			final boolean clientMode) throws Exception {

		final SSLContext sslContext = clientMode ?
			createSSLClientContext(config) :
			createSSLServerContext(config);

		checkState(sslContext != null, "%s it not enabled", SecurityOptions.SSL_ENABLED.key());

		return new SSLEngineFactory(
			sslContext,
			getEnabledProtocols(config),
			getEnabledCipherSuites(config),
			clientMode);
	}