@Test
	public void testCreateSSLClientContext() throws Exception {

		Configuration clientConfig = new Configuration();
		clientConfig.setBoolean(SecurityOptions.SSL_ENABLED, true);
		clientConfig.setString(SecurityOptions.SSL_TRUSTSTORE, "src/test/resources/local127.truststore");
		clientConfig.setString(SecurityOptions.SSL_TRUSTSTORE_PASSWORD, "password");

		SSLUtils.SSLContext clientContext = SSLUtils.createSSLClientContext(clientConfig);
		Assert.assertNotNull(clientContext);
	}