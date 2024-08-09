@Test
	public void testGetSource() {
		final Operator<?> fixture = new OpImpl(0);

		final JsonStream result = fixture.getSource();

		assertNotNull(result);
		assertEquals(0, result.getSource().getIndex());
		assertSame(fixture.getOutput(0), result);
	}