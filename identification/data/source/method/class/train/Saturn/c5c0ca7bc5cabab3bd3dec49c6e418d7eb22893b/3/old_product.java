public static void info(Logger logger, String eventName, String format, Object... msg) {
		if (logger.isInfoEnabled()) {
			logger.info(appendFormatAndEventName(format, eventName), msg);
		}
	}