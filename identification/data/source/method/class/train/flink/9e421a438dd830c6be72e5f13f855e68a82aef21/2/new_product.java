@Nullable
	public static SSLContext createSSLServerContext(Configuration sslConfig) throws Exception {

		Preconditions.checkNotNull(sslConfig);

		if (!getSSLEnabled(sslConfig)) {
			return null;
		}

		LOG.debug("Creating server SSL context from configuration");

		String keystoreFilePath = sslConfig.getString(SecurityOptions.SSL_KEYSTORE);
		String keystorePassword = sslConfig.getString(SecurityOptions.SSL_KEYSTORE_PASSWORD);
		String certPassword = sslConfig.getString(SecurityOptions.SSL_KEY_PASSWORD);
		String sslProtocolVersion = sslConfig.getString(SecurityOptions.SSL_PROTOCOL);
		int sessionCacheSize = sslConfig.getInteger(SecurityOptions.SSL_SESSION_CACHE_SIZE);
		int sessionTimeoutMs = sslConfig.getInteger(SecurityOptions.SSL_SESSION_TIMEOUT);
		int handshakeTimeoutMs = sslConfig.getInteger(SecurityOptions.SSL_HANDSHAKE_TIMEOUT);
		int closeNotifyFlushTimeoutMs = sslConfig.getInteger(SecurityOptions.SSL_CLOSE_NOTIFY_FLUSH_TIMEOUT);

		Preconditions.checkNotNull(keystoreFilePath, SecurityOptions.SSL_KEYSTORE.key() + " was not configured.");
		Preconditions.checkNotNull(keystorePassword, SecurityOptions.SSL_KEYSTORE_PASSWORD.key() + " was not configured.");
		Preconditions.checkNotNull(certPassword, SecurityOptions.SSL_KEY_PASSWORD.key() + " was not configured.");

		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		try (FileInputStream keyStoreFile = new FileInputStream(new File(keystoreFilePath))) {
			ks.load(keyStoreFile, keystorePassword.toCharArray());
		}

		// Set up key manager factory to use the server key store
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(
			KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(ks, certPassword.toCharArray());

		// Initialize the SSLContext
		javax.net.ssl.SSLContext serverSSLContext = javax.net.ssl.SSLContext.getInstance(sslProtocolVersion);
		serverSSLContext.init(kmf.getKeyManagers(), null, null);
		if (sessionCacheSize >= 0) {
			serverSSLContext.getServerSessionContext().setSessionCacheSize(sessionCacheSize);
		}
		if (sessionTimeoutMs >= 0) {
			serverSSLContext.getServerSessionContext().setSessionTimeout(sessionTimeoutMs / 1000);
		}

		return new SSLContext(serverSSLContext, handshakeTimeoutMs, closeNotifyFlushTimeoutMs);
	}