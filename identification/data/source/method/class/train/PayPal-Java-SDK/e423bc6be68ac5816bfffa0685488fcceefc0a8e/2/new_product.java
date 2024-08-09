public static SSLContext getSSLContext(KeyManager[] keymanagers)
			throws SSLConfigurationException {
		try {
			String protocol = CONFIG_MAP.get(Constants.SSLUTIL_PROTOCOL);
			if (protocol == null || protocol.equals("") || protocol.startsWith("SSL") || (protocol.startsWith("TLS") && !protocol.equals("TLSv1.2"))) {
				protocol = "TLSv1.2";
			}
			SSLContext ctx = SSLContext.getInstance(protocol);
			ctx.init(keymanagers, null, null);
			return ctx;
		} catch (Exception e) {
			throw new SSLConfigurationException(e.getMessage(), e);
		}
	}