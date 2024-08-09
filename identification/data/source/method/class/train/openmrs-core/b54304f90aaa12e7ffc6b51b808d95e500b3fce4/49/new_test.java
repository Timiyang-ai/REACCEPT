	@Test
	public void rethrowIfCause_shouldAllowAnIntermediateExceptionToBeRethrown() throws Exception {
		try {
			List<Class<? extends RuntimeException>> chain = Arrays.asList(NullPointerException.class,
			    IllegalArgumentException.class, IllegalStateException.class);
			throwExceptionChain(chain);
			
		}
		catch (Exception ex) {
			int numFound = 0;
			
			// Should be able to find the innermost NPE
			Exception innermost = null;
			try {
				ExceptionUtil.rethrowIfCause(ex, NullPointerException.class);
			}
			catch (Exception cause) {
				Assert.assertNull(cause.getCause());
				innermost = cause;
				++numFound;
			}
			
			// Should be able to find the middle IllegalArgumentException
			try {
				ExceptionUtil.rethrowIfCause(ex, IllegalArgumentException.class);
			}
			catch (Exception middle) {
				Assert.assertEquals(innermost, middle.getCause());
				++numFound;
			}
			
			// Should be able to find the outermost IllegalStateException
			try {
				ExceptionUtil.rethrowIfCause(ex, IllegalStateException.class);
			}
			catch (Exception outer) {
				Assert.assertEquals(ex, outer);
				++numFound;
			}
			
			Assert.assertEquals(3, numFound);
		}
	}