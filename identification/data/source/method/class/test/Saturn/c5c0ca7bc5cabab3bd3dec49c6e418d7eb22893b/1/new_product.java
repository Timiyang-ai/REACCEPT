public static void error(Logger logger, String eventName, String format, Object... arguments) {
		logger.error(constructFormatOrMsg(eventName, format), arguments);
	}