@Test
	public void testGeoAdd() {

		doReturn(Arrays.asList(Collections.singletonList(1L))).when(nativeConnection).closePipeline();
		super.testGeoAddBytes();
	}