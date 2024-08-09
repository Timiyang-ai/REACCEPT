	@Test
	public void toString_shouldPrintTheRangeIfHighAndLowAreNotNullAndNotInfinite() {
		DoubleRange r1 = new DoubleRange(1.0, 1.0);
		assertEquals(">= 1.0 and < 1.0", r1.toString());
	}