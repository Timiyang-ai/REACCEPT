@Test
	public void testGetOutputs() {
		final Operator<?> fixture = new OpImpl(0);

		final List<JsonStream> result = fixture.getOutputs();

		assertNotNull(result);
		assertEquals(1, result.size());

		final List<JsonStream> expectedResults = new ArrayList<JsonStream>();
		expectedResults.add(fixture.getOutput(0));
		assertEquals(expectedResults, result);
	}