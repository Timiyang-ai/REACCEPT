	@Test
	public void warn() {
		LogUtils.warn(log, "event", "this is warn");
		assertEquals("[event] msg=this is warn", testLogAppender.getLastMessage());

		LogUtils.warn(log, "event", "this is warn {}", "arg1");
		assertEquals("[event] msg=this is warn arg1", testLogAppender.getLastMessage());

		LogUtils.warn(log, "event", "this is warn {} {}", "arg1", "arg2");
		assertEquals("[event] msg=this is warn arg1 arg2", testLogAppender.getLastMessage());

		LogUtils.warn(log, "event", "this is warn {} {}", "arg1", "arg2", new ClassNotFoundException("com.abc"));
		assertEquals("[event] msg=this is warn arg1 arg2", testLogAppender.getLastMessage());
		assertEquals("com.abc", testLogAppender.getLastEvent().getThrowableProxy().getMessage());

		LogUtils.warn(log, "event", "this is warn {}", "arg1", new Error("com.def"));
		assertEquals("[event] msg=this is warn arg1", testLogAppender.getLastMessage());
		assertEquals("com.def", testLogAppender.getLastEvent().getThrowableProxy().getMessage());

		LogUtils.warn(log, "event", "this is warn", new Exception("com.def"));
		assertEquals("[event] msg=this is warn", testLogAppender.getLastMessage());
		assertEquals("com.def", testLogAppender.getLastEvent().getThrowableProxy().getMessage());
	}