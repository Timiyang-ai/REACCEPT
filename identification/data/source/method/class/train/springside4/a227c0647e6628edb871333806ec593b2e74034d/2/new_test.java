	@Test
	public void convertReflectionExceptionToUnchecked() {
		IllegalArgumentException iae = new IllegalArgumentException();
		// ReflectionException,normal
		RuntimeException e = ReflectionUtil.convertReflectionExceptionToUnchecked(iae);
		assertThat(e).isEqualTo(iae);

		// InvocationTargetException,extract it's target exception.
		Exception ex = new Exception();
		e = ReflectionUtil.convertReflectionExceptionToUnchecked(new InvocationTargetException(ex));
		assertThat(e.getCause()).isEqualTo(ex);

		// UncheckedException, ignore it.
		RuntimeException re = new RuntimeException("abc");
		e = ReflectionUtil.convertReflectionExceptionToUnchecked(re);
		assertThat(e).hasMessage("abc");

		// Unexcepted Checked exception.
		e = ReflectionUtil.convertReflectionExceptionToUnchecked(ex);
		assertThat(e).isInstanceOf(UncheckedException.class);
	}