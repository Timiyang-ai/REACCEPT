public static SSLContext getSSLContext(KeyManager[] keymanagers)
			throws SSLConfigurationException {
		try {
			SSLContext ctx = SSLContext.getInstance(CONFIG_MAP.get(Constants.SSLUTIL_PROTOCOL)); // TLS, SSLv3, SSL
			ctx.init(keymanagers, null, null);
			return ctx;
		} catch (Exception e) {
			throw new SSLConfigurationException(e.getMessage(), e);
		}
	}