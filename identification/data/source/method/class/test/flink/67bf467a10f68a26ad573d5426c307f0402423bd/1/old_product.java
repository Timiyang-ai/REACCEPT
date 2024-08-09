public static SSLContext createSSLClientContext(Configuration sslConfig) throws Exception {

		Preconditions.checkNotNull(sslConfig);
		SSLContext clientSSLContext = null;

		if (getSSLEnabled(sslConfig)) {
			LOG.debug("Creating client SSL context from configuration");

			String trustStoreFilePath = sslConfig.getString(
				ConfigConstants.SECURITY_SSL_TRUSTSTORE,
				null);
			String trustStorePassword = sslConfig.getString(
				ConfigConstants.SECURITY_SSL_TRUSTSTORE_PASSWORD,
				null);
			String sslProtocolVersion = sslConfig.getString(
				ConfigConstants.SECURITY_SSL_PROTOCOL,
				ConfigConstants.DEFAULT_SECURITY_SSL_PROTOCOL);

			Preconditions.checkNotNull(trustStoreFilePath, ConfigConstants.SECURITY_SSL_TRUSTSTORE + " was not configured.");
			Preconditions.checkNotNull(trustStorePassword, ConfigConstants.SECURITY_SSL_TRUSTSTORE_PASSWORD + " was not configured.");

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