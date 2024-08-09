public void startup() throws Exception {
		try {
			String sessionId = options.getSessionId();
			if (sessionId == null) {
				sessionId = createSessionId();
			}
			data.setSessionId(sessionId);
			output = createAgentOutput();
			output.startup(options, data);
			if (options.getJmx()) {
				jmxRegistration = new JmxRegistration(this);
			}
		} catch (final Exception e) {
			logger.logExeption(e);
			throw e;
		}
	}