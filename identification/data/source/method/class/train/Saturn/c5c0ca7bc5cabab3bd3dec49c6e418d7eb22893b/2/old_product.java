public static void debug(Logger logger, String eventName, String format, Object... msg) {
		if (logger.isDebugEnabled()) {
			logger.debug(appendFormatAndEventName(format, eventName), msg);
		}
	}