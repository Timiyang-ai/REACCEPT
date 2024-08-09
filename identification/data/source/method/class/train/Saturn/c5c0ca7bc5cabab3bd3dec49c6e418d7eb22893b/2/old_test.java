	@Test
	public void debug() {
		LogUtils.debug(log, "event", "this is debug");
		assertEquals("[event] msg=this is debug", testLogAppender.getLastMessage());

		LogUtils.debug(log, "event", "this is debug {}", "arg1");
		assertEquals("[event] msg=this is debug arg1", testLogAppender.getLastMessage());

		LogUtils.debug(log, "event", "this is debug {} {}", "arg1", "arg2");
		assertEquals("[event] msg=this is debug arg1 arg2", testLogAppender.getLastMessage());

		LogUtils.debug(log, "event", "this is debug {} {}", "arg1", "arg2", new ClassNotFoundException("com.abc"));
		assertEquals("[event] msg=this is debug arg1 arg2", testLogAppender.getLastMessage());
		assertEquals("com.abc", testLogAppender.getLastEvent().getThrowableProxy().getMessage());

		LogUtils.debug(log, "event", "this is debug {}", "arg1", new Error("com.def"));
		assertEquals("[event] msg=this is debug arg1", testLogAppender.getLastMessage());
		assertEquals("com.def", testLogAppender.getLastEvent().getThrowableProxy().getMessage());

		LogUtils.debug(log, "event", "this is debug", new Exception("com.def"));
		assertEquals("[event] msg=this is debug", testLogAppender.getLastMessage());
		assertEquals("com.def", testLogAppender.getLastEvent().getThrowableProxy().getMessage());
	}