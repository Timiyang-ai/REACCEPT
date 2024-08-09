	@Test
	public void contains_shouldReturnTrueIfParameterIsInRange() {
		DoubleRange r1 = new DoubleRange(0.0, 1.0);
		Double d = 0.5;
		assertTrue(r1.contains(d));
	}