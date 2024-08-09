@Test
	public void test_projectGeoPoint_2() {
		final int latE6 = 52370816;
		final int lonE6 = 9735936;
		final int zoom = 8;

		final GeoPoint point = Mercator.projectGeoPoint(latE6, lonE6, zoom, null);

		assertEquals("TODO describe test", 84, point.getLatitudeE6());
		assertEquals("TODO describe test", 134, point.getLongitudeE6());
	}