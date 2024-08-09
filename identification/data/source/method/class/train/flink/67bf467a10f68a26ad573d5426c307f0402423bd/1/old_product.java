public static SSLContext createSSLServerContext(Configuration sslConfig) throws Exception {

		Preconditions.checkNotNull(sslConfig);
		SSLContext serverSSLContext = null;

		if (getSSLEnabled(sslConfig)) {
			LOG.debug("Creating server SSL context from configuration");

			String keystoreFilePath = sslConfig.getString(
				ConfigConstants.SECURITY_SSL_KEYSTORE,
				null);

			String keystorePassword = sslConfig.getString(
				ConfigConstants.SECURITY_SSL_KEYSTORE_PASSWORD,
				null);

			String certPassword = sslConfig.getString(
				ConfigConstants.SECURITY_SSL_KEY_PASSWORD,
				null);

			String sslProtocolVersion = sslConfig.getString(
				ConfigConstants.SECURITY_SSL_PROTOCOL,
				ConfigConstants.DEFAULT_SECURITY_SSL_PROTOCOL);

			Preconditions.checkNotNull(keystoreFilePath, ConfigConstants.SECURITY_SSL_KEYSTORE + " was not configured.");
			Preconditions.checkNotNull(keystorePassword, ConfigConstants.SECURITY_SSL_KEYSTORE_PASSWORD + " was not configured.");
			Preconditions.checkNotNull(certPassword, ConfigConstants.SECURITY_SSL_KEY_PASSWORD + " was not configured.");

			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			FileInputStream keyStoreFile = null;
			try {
				keyStoreFile = new FileInputStream(new File(keystoreFilePath));
				ks.load(keyStoreFile, keystorePassword.toCharArray());
			} finally {
				if (keyStoreFile != null) {
					keyStoreFile.close();
				}
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