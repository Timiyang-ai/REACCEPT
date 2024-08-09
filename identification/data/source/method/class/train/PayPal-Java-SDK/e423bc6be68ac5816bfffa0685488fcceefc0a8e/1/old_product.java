public static SSLContext getSSLContext(KeyManager[] keymanagers)
			throws SSLConfigurationException {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS"); // TLS, SSLv3, SSL
			ctx.init(keymanagers, null, null);
			return ctx;
		} catch (Exception e) {
			throw new SSLConfigurationException(e.getMessage(), e);
		}
	}