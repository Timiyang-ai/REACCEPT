@Test
	public void testGetInputs() {
		final Operator<?> input1 = new OpImpl(0);
		final Operator<?> input2 = new OpImpl(1);
		final Operator<?> fixture = new OpImpl(0).withInputs(input1, input2);

		final List<JsonStream> result = fixture.getInputs();

		assertNotNull(result);
		assertEquals(2, result.size());

		final List<JsonStream> expectedResults = new ArrayList<JsonStream>();
		expectedResults.add(input1.getOutput(0));
		expectedResults.add(input2.getOutput(0));
		assertEquals(expectedResults, result);
	}