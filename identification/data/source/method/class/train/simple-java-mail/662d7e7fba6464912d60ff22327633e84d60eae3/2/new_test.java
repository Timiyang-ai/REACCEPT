	@Test
	public void trustAllHosts_PLAIN() {
		new MailSender(session, createDummyOperationalConfig(EMPTY_LIST, true, false), createEmptyProxyConfig(), SMTP);
		assertThat(session.getProperties().getProperty("mail.smtp.ssl.trust")).isEqualTo("*");
		assertThat(session.getProperties().getProperty("mail.smtp.ssl.checkserveridentity")).isNull();
		assertThat(session.getProperties().getProperty("mail.smtps.ssl.checkserveridentity")).isNull();
		new MailSender(session, createDummyOperationalConfig(EMPTY_LIST, false, true), createEmptyProxyConfig(), SMTP);
		assertThat(session.getProperties().getProperty("mail.smtp.ssl.trust")).isNull();
		assertThat(session.getProperties().getProperty("mail.smtp.ssl.checkserveridentity")).isNull();
		assertThat(session.getProperties().getProperty("mail.smtps.ssl.checkserveridentity")).isNull();
	}