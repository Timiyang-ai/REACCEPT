@Nullable
	public static SSLContext createSSLServerContext(Configuration sslConfig) throws Exception {

		Preconditions.checkNotNull(sslConfig);
		SSLContext serverSSLContext = null;

		if (getSSLEnabled(sslConfig)) {
			LOG.debug("Creating server SSL context from configuration");

			String keystoreFilePath = sslConfig.getString(SecurityOptions.SSL_KEYSTORE);

			String keystorePassword = sslConfig.getString(SecurityOptions.SSL_KEYSTORE_PASSWORD);

			String certPassword = sslConfig.getString(SecurityOptions.SSL_KEY_PASSWORD);

			String sslProtocolVersion = sslConfig.getString(SecurityOptions.SSL_PROTOCOL);

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
			serverSSLContext = SSLContext.getInstance(sslProtocolVersion);
			serverSSLContext.init(kmf.getKeyManagers(), null, null);
		}

		return serverSSLContext;
	}