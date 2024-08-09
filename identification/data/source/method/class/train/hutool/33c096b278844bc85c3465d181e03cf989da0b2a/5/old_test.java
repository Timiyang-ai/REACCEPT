	@Test
	public void cleanBlankTest(){
		//包含：制表符、英文空格、不间断空白符、全角空格
		String str = "	 你 好　";
		String cleanBlank = StrUtil.cleanBlank(str);
		Assert.assertEquals("你好", cleanBlank);
	}