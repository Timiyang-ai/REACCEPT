@Test
	public void testGeoAdd(){
		doReturn(Arrays.asList(new Object[] { Arrays.asList(new Object[] { 1l })})).when(nativeConnection).closePipeline();
		super.testGeoAddBytes();
	}