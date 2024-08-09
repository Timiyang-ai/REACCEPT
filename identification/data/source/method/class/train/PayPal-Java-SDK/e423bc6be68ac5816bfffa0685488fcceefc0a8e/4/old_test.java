	@Test
	public void getSSLContextTest() throws SSLConfigurationException {
		Assert.assertNotNull(SSLUtil.getSSLContext(null));
	}