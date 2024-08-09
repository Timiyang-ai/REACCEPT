	@Test
	public void compareTo_shouldReturnPlus1IfThisLowIsGreaterThanOtherLow() {
		DoubleRange r1 = new DoubleRange(1.0, 2.0);
		DoubleRange r2 = new DoubleRange(0.0, 2.0);
		assertEquals(1, r1.compareTo(r2));
	}