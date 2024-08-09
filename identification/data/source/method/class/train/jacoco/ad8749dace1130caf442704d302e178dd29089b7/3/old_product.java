public void startup() {
		try {
			String sessionId = options.getSessionId();
			if (sessionId == null) {
				sessionId = createSessionId();
			}
			data.setSessionId(sessionId);
			controller = createAgentController();
			controller.startup(options, data);
		} catch (final Exception e) {
			logger.logExeption(e);
		}
	}