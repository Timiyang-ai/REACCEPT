public static final void useConsoleLoggers() {
		String name = LoggerFactory.class.getName();
		LoggerFactory loggerFactory = new ConsoleLoggerFactory();
		loggerFactory.getLogger(name).debug("Using Console logging");
		LOGGER_FACTORY = loggerFactory;
	}