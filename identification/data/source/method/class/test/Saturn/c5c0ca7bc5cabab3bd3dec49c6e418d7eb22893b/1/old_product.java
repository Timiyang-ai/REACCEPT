public static void error(Logger logger, String eventName, String format, Object... msg) {
		if (logger.isErrorEnabled()) {
			logger.error(appendFormatAndEventName(format, eventName), msg);
		}
	}