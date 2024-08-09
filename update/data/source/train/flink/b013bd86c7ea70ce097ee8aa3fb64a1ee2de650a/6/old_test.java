@Test
	public void testSetInputs() {
		Operator input1 = new OpImpl(0);
		Operator input2 = new OpImpl(1);
		Operator fixture = new OpImpl(0, input1, input2);

		Operator newInput = new OpImpl(2);
		fixture.setInputs(Arrays.asList(newInput));

		assertEquals(1, fixture.getInputs().size());
		assertEquals(Arrays.asList(newInput.getOutput(0)), fixture.getInputs());
	}