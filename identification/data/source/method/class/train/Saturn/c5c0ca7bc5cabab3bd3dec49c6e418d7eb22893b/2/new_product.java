public static void debug(Logger logger, String eventName, String format, Object... arguments) {
		// 因为debug日志很少打开，所以先判断，如果打开，然后才拼接format
		if (logger.isDebugEnabled()) {
			logger.debug(constructFormatOrMsg(eventName, format), arguments);
		}
	}