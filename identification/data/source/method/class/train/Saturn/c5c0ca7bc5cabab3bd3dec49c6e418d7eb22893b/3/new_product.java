public static void info(Logger logger, String eventName, String format, Object... arguments) {
		logger.info(constructFormatOrMsg(eventName, format), arguments);
	}