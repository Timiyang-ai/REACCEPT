	@Test
	public void getRootCause() {
		IOException ioexception = new IOException("my exception");
		IllegalStateException illegalStateException = new IllegalStateException(ioexception);
		RuntimeException runtimeException = new RuntimeException(illegalStateException);

		assertThat(ExceptionUtil.getRootCause(runtimeException)).isSameAs(ioexception);
		// 无cause
		assertThat(ExceptionUtil.getRootCause(ioexception)).isSameAs(ioexception);
	}