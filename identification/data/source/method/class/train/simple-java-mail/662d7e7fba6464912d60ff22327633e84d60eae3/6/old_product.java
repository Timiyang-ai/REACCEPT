public void trustAllHosts(boolean trustAllHosts) {
		session.getProperties().remove("mail.smtp.ssl.trust");
		if (trustAllHosts) {
			session.getProperties().setProperty("mail.smtp.ssl.trust", "*");
		}
	}