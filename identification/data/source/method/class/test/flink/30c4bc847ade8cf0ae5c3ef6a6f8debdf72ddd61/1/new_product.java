public static SSLEngineFactory createRestServerSSLEngineFactory(final Configuration config) throws Exception {
		SSLContext sslContext = createRestServerSSLContext(config);
		if (sslContext == null) {
			throw new IllegalConfigurationException("SSL is not enabled for REST endpoints.");
		}

		return new SSLEngineFactory(
				sslContext,
				getEnabledProtocols(config),
				getEnabledCipherSuites(config),
				false);
	}