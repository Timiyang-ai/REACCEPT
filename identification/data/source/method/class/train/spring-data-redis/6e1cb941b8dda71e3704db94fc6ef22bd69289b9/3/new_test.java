@Test
	public void testGeoAdd() {

		doReturn(1l).when(nativeConnection).geoAdd(fooBytes, new Point(1.23232, 34.2342434), barBytes);

		actual.add(connection.geoAdd(foo, new Point(1.23232, 34.2342434), bar));
		verifyResults(Collections.singletonList(1L));
	}