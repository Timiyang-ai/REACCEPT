	@Test
	public void test_destinationPoint_north_west_here() {
		// this test is based on the actual result, not calculated expectations,
		// but it is at least a basic sanity check for rounding errors and regression
		final GeoPoint start = new GeoPoint(52.387524, 4.891604);
		final GeoPoint end = new GeoPoint(52.3906999098817, 4.886399738626785);
		assertEquals("destinationPoint north west", end, start.destinationPoint(500, -45));
	}