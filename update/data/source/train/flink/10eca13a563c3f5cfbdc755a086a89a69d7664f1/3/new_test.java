@Test
	public void testGetSource() {
		final Operator<?> fixture = new OpImpl(0);

		final Operator<?>.Output result = fixture.getSource();

		assertNotNull(result);
		assertEquals(0, result.getIndex());
		assertSame(fixture.getOutput(0), result);
	}