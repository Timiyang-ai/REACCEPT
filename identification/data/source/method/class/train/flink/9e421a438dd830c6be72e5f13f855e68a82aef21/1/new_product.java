@Nullable
	public static SSLContext createSSLClientContext(Configuration sslConfig) throws Exception {

		Preconditions.checkNotNull(sslConfig);

		if (!getSSLEnabled(sslConfig)) {
			return null;
		}

		LOG.debug("Creating client SSL context from configuration");

		String trustStoreFilePath = sslConfig.getString(SecurityOptions.SSL_TRUSTSTORE);
		String trustStorePassword = sslConfig.getString(SecurityOptions.SSL_TRUSTSTORE_PASSWORD);
		String sslProtocolVersion = sslConfig.getString(SecurityOptions.SSL_PROTOCOL);
		int sessionCacheSize = sslConfig.getInteger(SecurityOptions.SSL_SESSION_CACHE_SIZE);
		int sessionTimeoutMs = sslConfig.getInteger(SecurityOptions.SSL_SESSION_TIMEOUT);
		int handshakeTimeoutMs = sslConfig.getInteger(SecurityOptions.SSL_HANDSHAKE_TIMEOUT);
		int closeNotifyFlushTimeoutMs = sslConfig.getInteger(SecurityOptions.SSL_CLOSE_NOTIFY_FLUSH_TIMEOUT);

		Preconditions.checkNotNull(trustStoreFilePath, SecurityOptions.SSL_TRUSTSTORE.key() + " was not configured.");
		Preconditions.checkNotNull(trustStorePassword, SecurityOptions.SSL_TRUSTSTORE_PASSWORD.key() + " was not configured.");

		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());

		try (FileInputStream trustStoreFile = new FileInputStream(new File(trustStoreFilePath))) {
			trustStore.load(trustStoreFile, trustStorePassword.toCharArray());
		}

		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
			TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(trustStore);

		javax.net.ssl.SSLContext clientSSLContext = javax.net.ssl.SSLContext.getInstance(sslProtocolVersion);
		clientSSLContext.init(null, trustManagerFactory.getTrustManagers(), null);
		if (sessionCacheSize >= 0) {
			clientSSLContext.getClientSessionContext().setSessionCacheSize(sessionCacheSize);
		}
		if (sessionTimeoutMs >= 0) {
			clientSSLContext.getClientSessionContext().setSessionTimeout(sessionTimeoutMs / 1000);
		}
		return new SSLContext(clientSSLContext, handshakeTimeoutMs, closeNotifyFlushTimeoutMs);
	}