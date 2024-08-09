	@Test
	public void extractMultiTest() {
		// 抽取多个分组然后把它们拼接起来
		String resultExtractMulti = ReUtil.extractMulti("(\\w)aa(\\w)", content, "$1-$2");
		Assert.assertEquals("Z-a", resultExtractMulti);
	}