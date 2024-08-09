@Test
	public void testGetOutput() {
		Operator fixture = new OpImpl(0);

		Operator.Output result = fixture.getOutput(0);

		assertNotNull(result);
		assertEquals(0, result.getIndex());
	}