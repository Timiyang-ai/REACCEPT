public void shutdown() {
		try {
			if (options.getDumpOnExit()) {
				output.writeExecutionData(false);
			}
			output.shutdown();
			if (options.getJmx()) {
				ManagementFactory.getPlatformMBeanServer().unregisterMBean(
						new ObjectName(JMX_NAME));
			}
		} catch (final Exception e) {
			logger.logExeption(e);
		}
	}