public void startup() {
		try {
			String sessionId = options.getSessionId();
			if (sessionId == null) {
				sessionId = createSessionId();
			}
			data.setSessionId(sessionId);
			output = createAgentOutput();
			output.startup(options, data);
			if (options.getJmx()) {
				ManagementFactory.getPlatformMBeanServer().registerMBean(
						new StandardMBean(this, IAgent.class),
						new ObjectName(JMX_NAME));
			}
		} catch (final Exception e) {
			logger.logExeption(e);
		}
	}