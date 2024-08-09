	@Test
	public void test_distanceTo_itself() {
		final double distancePrecisionDelta = 0;
		final int iterations = 100;
		for (int i = 0 ; i < iterations ; i ++) {
			final GeoPoint target = new GeoPoint(getRandomLatitude(), getRandomLongitude());
			final GeoPoint other = new GeoPoint(target);
			assertEquals("distance to self is zero for " + target, 0, target.distanceToAsDouble(other), distancePrecisionDelta);
			assertEquals("reverse distance to self is zero for " + other, 0, other.distanceToAsDouble(target), distancePrecisionDelta);
		}
	}