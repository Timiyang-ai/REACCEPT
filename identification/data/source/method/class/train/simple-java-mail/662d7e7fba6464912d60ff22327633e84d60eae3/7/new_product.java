private void trustHosts(@Nonnull final List<String>  hosts) {
		trustAllHosts(false);
		if (!hosts.isEmpty()) {
			if (transportStrategy == null) {
				throw new MailSenderException(MailSenderException.CANNOT_SET_TRUST_WITHOUT_TRANSPORTSTRATEGY);
			}
			final StringBuilder builder = new StringBuilder(getFirst(hosts));
			for (int i = 1; i < hosts.size(); i++) {
				builder.append(" ").append(hosts.get(i));
			}
			session.getProperties().setProperty(transportStrategy.propertyNameSSLTrust(), builder.toString());
		}
	}