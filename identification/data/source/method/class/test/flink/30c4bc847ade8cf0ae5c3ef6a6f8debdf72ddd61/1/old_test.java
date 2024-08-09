@Test
	public void testCreateSSLEngineFactory() throws Exception {
		Configuration serverConfig = new Configuration();
		serverConfig.setBoolean(SecurityOptions.SSL_ENABLED, true);
		serverConfig.setString(SecurityOptions.SSL_KEYSTORE, "src/test/resources/local127.keystore");
		serverConfig.setString(SecurityOptions.SSL_KEYSTORE_PASSWORD, "password");
		serverConfig.setString(SecurityOptions.SSL_KEY_PASSWORD, "password");
		serverConfig.setString(SecurityOptions.SSL_PROTOCOL, "TLSv1");
		serverConfig.setString(SecurityOptions.SSL_ALGORITHMS, "TLS_DHE_RSA_WITH_AES_128_CBC_SHA,TLS_DHE_RSA_WITH_AES_128_CBC_SHA256");

		final SSLEngineFactory serverSSLEngineFactory = SSLUtils.createServerSSLEngineFactory(serverConfig);
		final SSLEngine sslEngine = serverSSLEngineFactory.createSSLEngine();

		assertThat(
			Arrays.asList(sslEngine.getEnabledProtocols()),
			contains("TLSv1"));
		assertThat(
			Arrays.asList(sslEngine.getEnabledCipherSuites()),
			containsInAnyOrder("TLS_DHE_RSA_WITH_AES_128_CBC_SHA", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256"));
	}