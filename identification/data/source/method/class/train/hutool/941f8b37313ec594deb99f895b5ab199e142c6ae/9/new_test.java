	@Test
	public void listFileNamesTest() {
		List<String> names = FileUtil.listFileNames("classpath:");
		Assert.assertTrue(names.contains("hutool.jpg"));
		
		names = FileUtil.listFileNames("");
		Assert.assertTrue(names.contains("hutool.jpg"));
		
		names = FileUtil.listFileNames(".");
		Assert.assertTrue(names.contains("hutool.jpg"));
	}