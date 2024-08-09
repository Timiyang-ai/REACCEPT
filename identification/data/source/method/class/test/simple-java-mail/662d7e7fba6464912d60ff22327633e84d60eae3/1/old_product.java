public void trustAllHosts(final boolean trustAllHosts) {
		session.getProperties().remove("mail.smtp.ssl.trust");
		if (trustAllHosts) {
			session.getProperties().setProperty("mail.smtp.ssl.trust", "*");
		}
	}