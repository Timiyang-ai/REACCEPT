@Test
	public void testCreateSSLClientContext() throws Exception {

		Configuration clientConfig = new Configuration();
		clientConfig.setBoolean(ConfigConstants.SECURITY_SSL_ENABLED, true);
		clientConfig.setString(ConfigConstants.SECURITY_SSL_TRUSTSTORE, "src/test/resources/local127.truststore");
		clientConfig.setString(ConfigConstants.SECURITY_SSL_TRUSTSTORE_PASSWORD, "password");

		SSLContext clientContext = SSLUtils.createSSLClientContext(clientConfig);
		Assert.assertNotNull(clientContext);
	}