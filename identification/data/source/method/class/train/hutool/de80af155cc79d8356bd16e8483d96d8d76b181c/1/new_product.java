public String send() throws MailException {
		try {
			return doSend();
		} catch (MessagingException e) {
			throw new MailException(e);
		}
	}