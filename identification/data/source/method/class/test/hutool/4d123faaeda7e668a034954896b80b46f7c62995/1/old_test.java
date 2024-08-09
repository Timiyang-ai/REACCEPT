	@Test
	public void trimTest(){
		String blank = "	 哈哈 　";
		String trim = StrUtil.trim(blank);
		Assert.assertEquals("哈哈", trim);
	}