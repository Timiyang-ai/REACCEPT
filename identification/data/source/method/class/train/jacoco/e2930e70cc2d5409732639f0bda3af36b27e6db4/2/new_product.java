public void shutdown() {
		try {
			if (options.getDumpOnExit()) {
				output.writeExecutionData(false);
			}
			output.shutdown();
			if (jmxRegistration != null) {
				jmxRegistration.call();
			}
		} catch (final Exception e) {
			logger.logExeption(e);
		}
	}