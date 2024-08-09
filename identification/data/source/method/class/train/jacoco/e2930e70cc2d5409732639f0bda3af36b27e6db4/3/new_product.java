public void shutdown() {
		try {
			if (options.getDumpOnExit()) {
				controller.writeExecutionData(false);
			}
			controller.shutdown();
		} catch (final Exception e) {
			logger.logExeption(e);
		}
	}