public static void warn(Logger logger, String eventName, String format, Object... arguments) {
		logger.warn(constructFormatOrMsg(eventName, format), arguments);
	}