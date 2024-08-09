	@Test
	public void getFirstNumberTest() {
		// 找到匹配的第一个数字
		Integer resultGetFirstNumber = ReUtil.getFirstNumber(content);
		Assert.assertEquals(Integer.valueOf(1234), resultGetFirstNumber);
	}