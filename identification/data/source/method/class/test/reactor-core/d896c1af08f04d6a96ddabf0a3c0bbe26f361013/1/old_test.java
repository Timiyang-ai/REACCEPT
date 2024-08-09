	@Test
	public void addThrowable() {
		Throwable e1 = new IllegalStateException("add1");
		Throwable e2 = new IllegalArgumentException("add2");
		Throwable e3 = new OutOfMemoryError("add3");

		assertThat(addThrowable).isNull();

		Exceptions.addThrowable(ADD_THROWABLE, this, e1);

		assertThat(addThrowable).isSameAs(e1);

		Exceptions.addThrowable(ADD_THROWABLE, this, e2);

		assertThat(Exceptions.isMultiple(addThrowable)).isTrue();
		assertThat(addThrowable)
				.hasSuppressedException(e1)
				.hasSuppressedException(e2);

		Exceptions.addThrowable(ADD_THROWABLE, this, e3);

		assertThat(Exceptions.isMultiple(addThrowable)).isTrue();
		assertThat(addThrowable)
				.hasSuppressedException(e1)
				.hasSuppressedException(e2)
				.hasSuppressedException(e3);
	}