@Test
	public void test_projectGeoPoint_1() {
        final GeoPoint hannover = new GeoPoint(52370816, 9735936);
		final int zoom = 8;

		final BasicPoint point = Mercator.projectGeoPoint(hannover, zoom, null);
		
		assertEquals("TODO describe test", 134, point.x);
		assertEquals("TODO describe test", 84, point.y);
	}