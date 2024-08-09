	@Test
	public void escapeTest() {
		//转义给定字符串，为正则相关的特殊符号转义
		String escape = ReUtil.escape("我有个$符号{}");
		Assert.assertEquals("我有个\\$符号\\{\\}", escape);
	}