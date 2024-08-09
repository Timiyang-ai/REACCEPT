	@Test
	public void unchecked() {
		// convert Exception to RuntimeException with cause
		Exception exception = new Exception("my exception");
		try {
			ExceptionUtil.unchecked(exception);
			fail("should fail before");
		} catch (Throwable t) {
			assertThat(t.getCause()).isSameAs(exception);
		}

		// do nothing of Error
		Error error = new LinkageError();
		try {
			ExceptionUtil.unchecked(error);
			fail("should fail before");
		} catch (Throwable t) {
			assertThat(t).isSameAs(error);
		}

		// do nothing of RuntimeException
		RuntimeException runtimeException = new RuntimeException("haha");
		try {
			ExceptionUtil.unchecked(runtimeException);
			fail("should fail before");
		} catch (Throwable t) {
			assertThat(t).isSameAs(runtimeException);
		}

	}