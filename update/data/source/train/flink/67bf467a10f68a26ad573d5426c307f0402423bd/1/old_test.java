@Test
	public void testCreateSSLServerContext() throws Exception {

		Configuration serverConfig = new Configuration();
		serverConfig.setBoolean(ConfigConstants.SECURITY_SSL_ENABLED, true);
		serverConfig.setString(ConfigConstants.SECURITY_SSL_KEYSTORE, "src/test/resources/local127.keystore");
		serverConfig.setString(ConfigConstants.SECURITY_SSL_KEYSTORE_PASSWORD, "password");
		serverConfig.setString(ConfigConstants.SECURITY_SSL_KEY_PASSWORD, "password");

		SSLContext serverContext = SSLUtils.createSSLServerContext(serverConfig);
		Assert.assertNotNull(serverContext);
	}