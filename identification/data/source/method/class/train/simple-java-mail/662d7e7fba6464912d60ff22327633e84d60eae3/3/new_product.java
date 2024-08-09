public void trustHosts(final String... hosts) {
		trustAllHosts(false);
		if (hosts.length > 0) {
			if (transportStrategy == null) {
				throw new MailSenderException(MailSenderException.CANNOT_SET_TRUST_WITHOUT_TRANSPORTSTRATEGY);
			}
			final StringBuilder builder = new StringBuilder(hosts[0]);
			for (int i = 1; i < hosts.length; i++) {
				builder.append(" ").append(hosts[i]);
			}
			session.getProperties().setProperty(transportStrategy.propertyNameSSLTrust(), builder.toString());
		}
	}