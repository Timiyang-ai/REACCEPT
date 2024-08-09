	@Test
	public void isMatchTest() {
		// 给定字符串是否匹配给定正则
		boolean isMatch = ReUtil.isMatch("\\w+[\u4E00-\u9FFF]+\\d+", content);
		Assert.assertTrue(isMatch);
	}