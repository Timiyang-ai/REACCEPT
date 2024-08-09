@Nullable
	public static SSLContext createSSLClientContext(Configuration sslConfig) throws Exception {

		Preconditions.checkNotNull(sslConfig);
		SSLContext clientSSLContext = null;

		if (getSSLEnabled(sslConfig)) {
			LOG.debug("Creating client SSL context from configuration");

			String trustStoreFilePath = sslConfig.getString(SecurityOptions.SSL_TRUSTSTORE);
			String trustStorePassword = sslConfig.getString(SecurityOptions.SSL_TRUSTSTORE_PASSWORD);
			String sslProtocolVersion = sslConfig.getString(SecurityOptions.SSL_PROTOCOL);

			Preconditions.checkNotNull(trustStoreFilePath, SecurityOptions.SSL_TRUSTSTORE.key() + " was not configured.");
			Preconditions.checkNotNull(trustStorePassword, SecurityOptions.SSL_TRUSTSTORE_PASSWORD.key() + " was not configured.");

			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());

			FileInputStream trustStoreFile = null;
			try {
				trustStoreFile = new FileInputStream(new File(trustStoreFilePath));
				trustStore.load(trustStoreFile, trustStorePassword.toCharArray());
			} finally {
				if (trustStoreFile != null) {
					trustStoreFile.close();
				}
			}

			TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
				TrustManagerFactory.getDefaultAlgorithm());
			trustManagerFactory.init(trustStore);

			clientSSLContext = SSLContext.getInstance(sslProtocolVersion);
			clientSSLContext.init(null, trustManagerFactory.getTrustManagers(), null);
		}

		return clientSSLContext;
	}