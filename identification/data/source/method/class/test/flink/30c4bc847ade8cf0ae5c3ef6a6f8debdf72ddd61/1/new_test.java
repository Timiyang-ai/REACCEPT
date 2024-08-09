@Test
	public void testCreateSSLEngineFactory() throws Exception {
		Configuration serverConfig = createInternalSslConfigWithKeyAndTrustStores();

		// set custom protocol and cipher suites
		serverConfig.setString(SecurityOptions.SSL_PROTOCOL, "TLSv1");
		serverConfig.setString(SecurityOptions.SSL_ALGORITHMS, "TLS_DHE_RSA_WITH_AES_128_CBC_SHA,TLS_DHE_RSA_WITH_AES_128_CBC_SHA256");

		final SSLEngineFactory serverSSLEngineFactory = SSLUtils.createInternalServerSSLEngineFactory(serverConfig);
		final SSLEngine sslEngine = serverSSLEngineFactory.createSSLEngine();

		assertEquals(1, sslEngine.getEnabledProtocols().length);
		assertEquals("TLSv1", sslEngine.getEnabledProtocols()[0]);

		assertEquals(2, sslEngine.getEnabledCipherSuites().length);
		assertThat(sslEngine.getEnabledCipherSuites(), arrayContainingInAnyOrder(
				"TLS_DHE_RSA_WITH_AES_128_CBC_SHA", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256"));
	}