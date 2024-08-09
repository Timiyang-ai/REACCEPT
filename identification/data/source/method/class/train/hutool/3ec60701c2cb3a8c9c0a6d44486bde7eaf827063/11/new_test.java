	@Test
	public void delTest() {
		//删除一个不存在的文件，应返回false
		boolean result = FileUtil.del("e:/Hutool_test_3434543533409843.txt");
		Assert.assertFalse(result);
	}