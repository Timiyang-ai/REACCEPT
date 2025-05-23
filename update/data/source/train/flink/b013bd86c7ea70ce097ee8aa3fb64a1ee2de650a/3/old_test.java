@Test
	public void testGetInputOperators() {
		Operator input1 = new OpImpl(0);
		Operator input2 = new OpImpl(1);
		Operator fixture = new OpImpl(0, input1, input2);

		assertSame(input1, fixture.getInputOperators().get(0));
		assertSame(input2, fixture.getInputOperators().get(1));
	}