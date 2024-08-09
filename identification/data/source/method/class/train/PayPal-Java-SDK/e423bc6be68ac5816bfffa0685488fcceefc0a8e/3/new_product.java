public static SSLContext getSSLContext(KeyManager[] keymanagers)
			throws SSLConfigurationException {
		try {
			SSLContext ctx = null;
			String protocol = CONFIG_MAP.get(Constants.SSLUTIL_PROTOCOL);
			try {
				ctx = SSLContext.getInstance("TLSv1.2");
			} catch (NoSuchAlgorithmException e) {
				log.warn("WARNING: Your system does not support TLSv1.2. Per PCI Security Council mandate (https://github.com/paypal/TLS-update), you MUST update to latest security library.");
				ctx = SSLContext.getInstance(protocol);
			}
			ctx.init(keymanagers, null, null);
			return ctx;
		} catch (Exception e) {
			throw new SSLConfigurationException(e.getMessage(), e);
		}
	}