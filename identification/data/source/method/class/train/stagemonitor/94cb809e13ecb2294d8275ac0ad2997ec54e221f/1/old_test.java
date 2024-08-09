	@Test
	public void excludeCallTree() throws Exception {
		assertFalse(interceptorContext.isExcludeCallTree());

		interceptorContext.excludeCallTree("reasons");

		assertTrue(interceptorContext.isExcludeCallTree());
	}