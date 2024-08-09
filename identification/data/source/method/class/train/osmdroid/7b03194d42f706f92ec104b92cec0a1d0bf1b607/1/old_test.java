@Test
	public void test_projectGeoPoint_2() {
		final int latE6 = 52370816;
		final int lonE6 = 9735936;
		final int zoom = 8;

		final int[] point = Mercator.projectGeoPoint(latE6, lonE6, zoom, null);

		assertEquals("TODO describe test", 84, point[0]);
		assertEquals("TODO describe test", 134, point[1]);
	}