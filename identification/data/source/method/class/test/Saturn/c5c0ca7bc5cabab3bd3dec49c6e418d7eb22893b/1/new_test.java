	@Test
	public void error() {
		LogUtils.error(log, "event", "this is error");
		assertEquals("[event] msg=this is error", testLogAppender.getLastMessage());

		LogUtils.error(log, "event", "this is error {}", "arg1");
		assertEquals("[event] msg=this is error arg1", testLogAppender.getLastMessage());

		LogUtils.error(log, "event", "this is error {} {}", "arg1", "arg2");
		assertEquals("[event] msg=this is error arg1 arg2", testLogAppender.getLastMessage());

		LogUtils.error(log, "event", "this is error {} {}", "arg1", "arg2", new ClassNotFoundException("com.abc"));
		assertEquals("[event] msg=this is error arg1 arg2", testLogAppender.getLastMessage());
		assertEquals("com.abc", testLogAppender.getLastEvent().getThrowableProxy().getMessage());

		LogUtils.error(log, "event", "this is error {}", "arg1", new Error("com.def"));
		assertEquals("[event] msg=this is error arg1", testLogAppender.getLastMessage());
		assertEquals("com.def", testLogAppender.getLastEvent().getThrowableProxy().getMessage());

		LogUtils.error(log, "event", "this is error", new Exception("com.def"));
		assertEquals("[event] msg=this is error", testLogAppender.getLastMessage());
		assertEquals("com.def", testLogAppender.getLastEvent().getThrowableProxy().getMessage());
	}