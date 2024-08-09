public static SSLContext getSSLContext(KeyManager[] keymanagers)
			throws SSLConfigurationException {
		try {
			SSLContext ctx = null;
			String protocol = CONFIG_MAP.get(Constants.SSLUTIL_PROTOCOL);
			try {
				ctx = SSLContext.getInstance("TLSv1.2");
			} catch (NoSuchAlgorithmException e) {
				log.warn("SECURITY WARNING: TLSv1.2 is not supported on this system. Please update your Java runtime to a version that supports TLSv1.2.");
				ctx = SSLContext.getInstance(protocol);
			}
			ctx.init(keymanagers, null, null);
			return ctx;
		} catch (Exception e) {
			throw new SSLConfigurationException(e.getMessage(), e);
		}
	}