@Test
	public void test_distanceTo_Equator_Smaller() {
		final double ratioDelta = 1E-5;
		final int iterations = 10;
		final double latitude = 0;
		double longitudeIncrement = 1;
		for (int i = 0 ; i < iterations ; i ++) {
			final double longitude1 = getRandomLongitude();
			final double longitude2 = longitude1 + longitudeIncrement;
			longitudeIncrement /= 10.;
			final GeoPoint target = new GeoPoint(latitude, longitude1);
			final GeoPoint other = new GeoPoint(latitude, longitude2);
			final double diff = getCleanLongitudeDiff(longitude1, longitude2);
			final double expected = GeoConstants.RADIUS_EARTH_METERS * diff * MathConstants.DEG2RAD;
			final double delta = expected * ratioDelta;
			assertEquals("distance between " + target + " and " + other,
					expected, target.distanceToAsDouble(other), delta);
		}
	}