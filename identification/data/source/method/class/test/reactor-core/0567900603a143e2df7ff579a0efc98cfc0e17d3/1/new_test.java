	@Test
	public void useConsoleLoggers() throws Exception {
		try {
			Loggers.useConsoleLoggers();
			Logger l = Loggers.getLogger("test");

			assertThat(l.getClass().getSimpleName()).isEqualTo("ConsoleLogger");
		}
		finally {
			Loggers.resetLoggerFactory();
		}
	}