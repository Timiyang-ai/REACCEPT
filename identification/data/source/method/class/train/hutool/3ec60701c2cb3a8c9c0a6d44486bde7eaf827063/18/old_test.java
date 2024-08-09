	@Test
	public void getAbsolutePathTest(){
		String absolutePath = FileUtil.getAbsolutePath("LICENSE-junit.txt");
		String absolutePath2 = FileUtil.getAbsolutePath(absolutePath);
		Assert.assertNotNull(absolutePath2);
		Assert.assertEquals(absolutePath, absolutePath2);
	}