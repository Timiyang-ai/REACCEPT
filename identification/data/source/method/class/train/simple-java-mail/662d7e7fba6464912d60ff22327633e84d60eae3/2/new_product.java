private void trustAllHosts(final boolean trustAllHosts) {
		if (transportStrategy != null) {
			session.getProperties().remove(transportStrategy.propertyNameSSLTrust());
			if (trustAllHosts) {
				session.getProperties().setProperty(transportStrategy.propertyNameSSLTrust(), "*");
			}
		} else {
			throw new MailSenderException(MailSenderException.CANNOT_SET_TRUST_WITHOUT_TRANSPORTSTRATEGY);
		}
	}