@Test
	public void testSetInput() {
		Operator input1 = new OpImpl(0);
		Operator input2 = new OpImpl(1);
		Operator fixture = new OpImpl(0, input1, input2);

		Operator newInput2 = new OpImpl(2);

		fixture.setInput(1, newInput2);

		assertSame(input1.getOutput(0), fixture.getInput(0));
		assertSame(newInput2.getOutput(0), fixture.getInput(1));
		assertNotSame(input2, newInput2);
	}