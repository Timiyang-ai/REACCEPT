public static void warn(Logger logger, String eventName, String format, Object... msg) {
		if (logger.isWarnEnabled()) {
			logger.warn(appendFormatAndEventName(format, eventName), msg);
		}
	}