@Test
	public void testGeoPos(){
		doReturn(Arrays.asList(new Object[] { geoCoordinates })).when(nativeConnection).exec();
		super.testGeoPos();
	}