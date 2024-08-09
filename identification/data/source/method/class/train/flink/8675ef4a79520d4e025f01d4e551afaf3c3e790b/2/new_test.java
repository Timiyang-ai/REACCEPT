@Test
	public void testSetInputs() {
		final Operator input1 = new OpImpl(0);
		final Operator input2 = new OpImpl(1);
		final Operator fixture = new OpImpl(0).withInputs(input1, input2);

		final Operator newInput = new OpImpl(2);
		fixture.setInputs(Arrays.asList(newInput));

		assertEquals(1, fixture.getInputs().size());
		assertEquals(Arrays.asList(newInput.getOutput(0)), fixture.getInputs());
	}