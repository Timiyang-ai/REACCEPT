public void trustHosts(final String... hosts) {
		trustAllHosts(false);
		if (hosts.length > 0) {
			final StringBuilder builder = new StringBuilder(hosts[0]);
			for (int i = 1; i < hosts.length; i++) {
				builder.append(" ").append(hosts[i]);
			}
			session.getProperties().setProperty("mail.smtp.ssl.trust", builder.toString());
		}
	}