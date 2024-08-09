	@Test
	public void trustHosts() {
		new MailSender(session, createDummyOperationalConfig(asList(), false, false), createEmptyProxyConfig(), SMTP);
		assertThat(session.getProperties().getProperty("mail.smtp.ssl.trust")).isNull();
		assertThat(session.getProperties().getProperty("mail.smtp.ssl.checkserveridentity")).isNull();
		assertThat(session.getProperties().getProperty("mail.smtps.ssl.checkserveridentity")).isNull();
		new MailSender(session, createDummyOperationalConfig(asList("a"), false, false), createEmptyProxyConfig(), SMTP);
		assertThat(session.getProperties().getProperty("mail.smtp.ssl.trust")).isEqualTo("a");
		assertThat(session.getProperties().getProperty("mail.smtp.ssl.checkserveridentity")).isNull();
		assertThat(session.getProperties().getProperty("mail.smtps.ssl.checkserveridentity")).isNull();
		new MailSender(session, createDummyOperationalConfig(asList("a", "b"), false, false), createEmptyProxyConfig(), SMTP);
		assertThat(session.getProperties().getProperty("mail.smtp.ssl.trust")).isEqualTo("a b");
		assertThat(session.getProperties().getProperty("mail.smtp.ssl.checkserveridentity")).isNull();
		assertThat(session.getProperties().getProperty("mail.smtps.ssl.checkserveridentity")).isNull();
		new MailSender(session, createDummyOperationalConfig(asList("a", "b", "c"), false, true), createEmptyProxyConfig(), SMTP);
		assertThat(session.getProperties().getProperty("mail.smtp.ssl.trust")).isEqualTo("a b c");
		assertThat(session.getProperties().getProperty("mail.smtp.ssl.checkserveridentity")).isNull();
		assertThat(session.getProperties().getProperty("mail.smtps.ssl.checkserveridentity")).isNull();
	}