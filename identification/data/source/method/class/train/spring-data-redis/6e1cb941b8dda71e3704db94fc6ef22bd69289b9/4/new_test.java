@Test
	public void testGeoDist() {
		doReturn(Arrays.asList(new Distance(102121.12d, DistanceUnit.METERS))).when(nativeConnection).closePipeline();
		super.testGeoDist();

	}