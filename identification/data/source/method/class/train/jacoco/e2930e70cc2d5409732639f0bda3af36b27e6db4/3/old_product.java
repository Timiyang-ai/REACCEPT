public void shutdown() {
		try {
			if (options.getDumpOnExit()) {
				controller.writeExecutionData();
			}
			controller.shutdown();
		} catch (final Exception e) {
			logger.logExeption(e);
		}
	}