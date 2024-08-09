@Test
	public void testGeoDist(){
		doReturn(Arrays.asList(new Object[] { 102121.12d })).when(nativeConnection).closePipeline();
		super.testGeoDist();
	}