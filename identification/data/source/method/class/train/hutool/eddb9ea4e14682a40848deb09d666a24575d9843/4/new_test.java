	@Test
	public void delFirstTest() {
		// 删除第一个匹配到的内容
		String resultDelFirst = ReUtil.delFirst("(\\w)aa(\\w)", content);
		Assert.assertEquals("ZZbbbccc中文1234", resultDelFirst);
	}