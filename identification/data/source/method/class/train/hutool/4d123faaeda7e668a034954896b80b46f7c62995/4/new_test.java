	@Test
	public void isBlankTest(){
		String blank = "	  　";
		Assert.assertTrue(StrUtil.isBlank(blank));
	}