@Test
	public void testSetInput() {
		final Operator input1 = new OpImpl(0);
		final Operator input2 = new OpImpl(1);
		final Operator fixture = new OpImpl(0, input1, input2);

		final Operator newInput2 = new OpImpl(2);

		fixture.setInput(1, newInput2);

		assertSame(input1.getOutput(0), fixture.getInput(0));
		assertSame(newInput2.getOutput(0), fixture.getInput(1));
		assertNotSame(input2, newInput2);
	}