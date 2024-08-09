@Test
	public void testGetOutput() {
		final Operator<?> fixture = new OpImpl(0);

		final Operator<?>.Output result = fixture.getOutput(0);

		assertNotNull(result);
		assertEquals(0, result.getIndex());
	}