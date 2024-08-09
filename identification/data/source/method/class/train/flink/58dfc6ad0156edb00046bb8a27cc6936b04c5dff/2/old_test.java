@Test
	public void testGetOutputs() {
		final Operator<?> fixture = new OpImpl(0);

		final List<Operator<?>.Output> result = fixture.getOutputs();

		assertNotNull(result);
		assertEquals(1, result.size());

		final List<Operator<?>.Output> expectedResults = new ArrayList<Operator<?>.Output>();
		expectedResults.add(fixture.getOutput(0));
		assertEquals(expectedResults, result);
	}