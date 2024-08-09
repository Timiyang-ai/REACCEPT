	@Test
	public void replaceTest() {
		String string = StrUtil.replace("aabbccdd", 2, 6, '*');
		Assert.assertEquals("aa****dd", string);
		string = StrUtil.replace("aabbccdd", 2, 12, '*');
		Assert.assertEquals("aa******", string);
	}