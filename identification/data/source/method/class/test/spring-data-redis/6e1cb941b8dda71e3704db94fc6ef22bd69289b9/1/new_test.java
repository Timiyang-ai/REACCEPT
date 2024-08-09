@Test
	public void testGeoPos() {

		doReturn(Arrays.asList(points)).when(nativeConnection).exec();
		super.testGeoPos();
	}