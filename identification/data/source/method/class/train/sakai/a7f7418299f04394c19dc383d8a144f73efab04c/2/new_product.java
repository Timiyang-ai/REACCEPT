public void init()
	{
		log.debug("init");
		// register Sakai permissions for this tool
		functionManager.registerFunction(PERM_ADMIN);
		functionManager.registerFunction(PERM_SEND);

		useSsl = configService.getBoolean(SAKAI_USE_SSL, DEFAULT_USE_SSL);
		if (useSsl) {
			protocol = "smtps";
		} else {
			protocol = "smtp";
		}
		useTls = configService.getBoolean(SAKAI_USE_TLS, DEFAULT_USE_TLS);
		sendPartial = configService.getBoolean(MailConstants.SAKAI_SENDPARTIAL, Boolean.TRUE);

		smtpHost = configService.getString(SAKAI_HOST, DEFAULT_SMTP_HOST);
		smtpPort = configService.getInt(SAKAI_PORT, DEFAULT_SMTP_PORT);
		smtpUser = configService.getString(SAKAI_USER);
		smtpPassword = configService.getString(SAKAI_PASSWORD);
		allowTransport = configService.getBoolean(SAKAI_ALLOW_TRANSPORT, Boolean.TRUE);
		smtpDebug = configService.getBoolean(SAKAI_DEBUG, DEFAULT_SMTP_DEBUG);

		// set the protocol to be used
		if (useSsl) {
			protocol = PROTOCOL_SMTPS;
		} else {
			protocol = PROTOCOL_SMTP;
		}

		// initialize timeout values
		connectionTimeout = configService.getString(SAKAI_CONNECTION_TIMEOUT, null);
		if (connectionTimeout == null) {
			connectionTimeout = configService.getString(propName(MAIL_CONNECTIONTIMEOUT), null);
		}
		
		timeout = configService.getString(SAKAI_TIMEOUT);
		if (connectionTimeout == null) {
			connectionTimeout = configService.getString(propName(MAIL_CONNECTIONTIMEOUT), null);
		}

		// check for smtp protocol labeled values for backwards compatibility
		if (PROTOCOL_SMTPS.equals(protocol))
		{
			if (connectionTimeout == null)
				connectionTimeout = configService.getString(propName(MAIL_CONNECTIONTIMEOUT, PROTOCOL_SMTP), null);
		
			if (timeout == null)
				timeout = configService.getString(propName(MailConstants.MAIL_TIMEOUT, PROTOCOL_SMTP), null);
		}
	}