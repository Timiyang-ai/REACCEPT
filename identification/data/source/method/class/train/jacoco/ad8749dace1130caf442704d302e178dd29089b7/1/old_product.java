public void startup() throws Exception {
		String sessionId = options.getSessionId();
		if (sessionId == null) {
			sessionId = createSessionId();
		}
		data.setSessionId(sessionId);
		controller = createAgentController();
		controller.startup(options, data);
	}