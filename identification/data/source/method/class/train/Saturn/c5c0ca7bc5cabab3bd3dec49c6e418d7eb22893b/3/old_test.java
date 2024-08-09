	@Test
	public void info() {
		LogUtils.info(log, "event", "this is info");
		assertEquals("[event] msg=this is info", testLogAppender.getLastMessage());

		LogUtils.info(log, "event", "this is info {}", "arg1");
		assertEquals("[event] msg=this is info arg1", testLogAppender.getLastMessage());

		LogUtils.info(log, "event", "this is info {} {}", "arg1", "arg2");
		assertEquals("[event] msg=this is info arg1 arg2", testLogAppender.getLastMessage());

		LogUtils.info(log, "event", "this is info {} {}", "arg1", "arg2", new ClassNotFoundException("com.abc"));
		assertEquals("[event] msg=this is info arg1 arg2", testLogAppender.getLastMessage());
		assertEquals("com.abc", testLogAppender.getLastEvent().getThrowableProxy().getMessage());

		LogUtils.info(log, "event", "this is info {}", "arg1", new Error("com.def"));
		assertEquals("[event] msg=this is info arg1", testLogAppender.getLastMessage());
		assertEquals("com.def", testLogAppender.getLastEvent().getThrowableProxy().getMessage());

		LogUtils.info(log, "event", "this is info", new Exception("com.def"));
		assertEquals("[event] msg=this is info", testLogAppender.getLastMessage());
		assertEquals("com.def", testLogAppender.getLastEvent().getThrowableProxy().getMessage());
	}