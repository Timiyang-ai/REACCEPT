@Test
	public void testGetOutputs() {
		final Operator<?> fixture = new OpImpl(0);

		final List<Operator<?>.Output> result = fixture.getOutputs();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(Arrays.asList(fixture.getOutput(0)), result);
	}