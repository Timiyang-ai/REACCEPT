	@Test
	public void unwrap() {
		RuntimeException re = new RuntimeException("my runtime");
		assertThat(ExceptionUtil.unwrap(re)).isSameAs(re);

		ExecutionException ee = new ExecutionException(re);
		assertThat(ExceptionUtil.unwrap(ee)).isSameAs(re);

		InvocationTargetException ie = new InvocationTargetException(re);
		assertThat(ExceptionUtil.unwrap(ie)).isSameAs(re);
		
		Exception e = new Exception("my exception");
		ExecutionException ee2 = new ExecutionException(e);
		try{
		ExceptionUtil.unwrapAndUnchecked(ee2);
		}catch (Throwable t) {
			assertThat(t).isInstanceOf(UncheckedException.class).hasCauseExactlyInstanceOf(Exception.class);
		}
	}