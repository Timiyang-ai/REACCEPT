static final EMailSentStatus invalid(final String errorMessage)
	{
		final boolean sentConnectionError = false;
		final String messageId = null;
		return new EMailSentStatus(errorMessage, sentConnectionError, messageId);
	}