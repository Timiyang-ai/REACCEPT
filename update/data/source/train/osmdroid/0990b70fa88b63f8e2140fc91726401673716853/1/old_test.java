@Test
	public void test_distanceTo_one() {
		final GeoPoint target = new GeoPoint(1.0, 1.0);
		final GeoPoint other = new GeoPoint(1.0, 1.0);
		assertEquals("distance to self is zero", 0, target.distanceTo(other));
	}