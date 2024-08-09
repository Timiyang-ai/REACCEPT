	@Test
	public void isErrorCallbackNotImplemented() {
		UnsupportedOperationException vanillaUnsupported = new UnsupportedOperationException("not error callback");
		UnsupportedOperationException t = errorCallbackNotImplemented(new IllegalStateException("in error callback"));

		assertThat(Exceptions.isErrorCallbackNotImplemented(vanillaUnsupported))
				.as("not error callback")
				.isFalse();
		assertThat(Exceptions.isErrorCallbackNotImplemented(t))
				.as("error callback")
				.isTrue();
	}