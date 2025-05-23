@Test
	public void testGetInputOperators() {
		final Operator input1 = new OpImpl(0);
		final Operator input2 = new OpImpl(1);
		final Operator fixture = new OpImpl(0).withInputs(input1, input2);

		assertSame(input1, fixture.getInputOperators().get(0));
		assertSame(input2, fixture.getInputOperators().get(1));
	}