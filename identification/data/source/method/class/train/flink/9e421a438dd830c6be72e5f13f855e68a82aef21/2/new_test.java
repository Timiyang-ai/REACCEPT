@Test
	public void testCreateSSLServerContext() throws Exception {

		Configuration serverConfig = new Configuration();
		serverConfig.setBoolean(SecurityOptions.SSL_ENABLED, true);
		serverConfig.setString(SecurityOptions.SSL_KEYSTORE, "src/test/resources/local127.keystore");
		serverConfig.setString(SecurityOptions.SSL_KEYSTORE_PASSWORD, "password");
		serverConfig.setString(SecurityOptions.SSL_KEY_PASSWORD, "password");

		SSLUtils.SSLContext serverContext = SSLUtils.createSSLServerContext(serverConfig);
		Assert.assertNotNull(serverContext);
	}