@Test
	public void testGetInputs() {
		final Operator input1 = new OpImpl(0);
		final Operator input2 = new OpImpl(1);
		final Operator fixture = new OpImpl(0, input1, input2);

		final List<Operator.Output> result = fixture.getInputs();

		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals(Arrays.asList(input1.getOutput(0), input2.getOutput(0)), result);
	}