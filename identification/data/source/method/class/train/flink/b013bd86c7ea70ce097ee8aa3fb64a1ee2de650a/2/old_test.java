@Test
	public void testGetInput() {
		Operator input1 = new OpImpl(0);
		Operator input2 = new OpImpl(1);
		Operator fixture = new OpImpl(0, input1, input2);

		assertSame(input1.getOutput(0), fixture.getInput(0));
		assertSame(input2.getOutput(0), fixture.getInput(1));
	}