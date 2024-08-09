	@Test
	public void test_bearingTo_north() {
		final GeoPoint target = new GeoPoint(0.0, 0.0);
		final GeoPoint other = new GeoPoint(10.0, 0.0);
		assertEquals("directly north", 0, Math.round(target.bearingTo(other)));
	}