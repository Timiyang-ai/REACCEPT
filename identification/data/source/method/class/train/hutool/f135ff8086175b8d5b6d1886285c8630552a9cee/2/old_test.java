	@Test
	public void lowerFirstTest() {
		StringBuilder sb = new StringBuilder("KEY");
		String s = StrUtil.lowerFirst(sb);
		Assert.assertEquals("kEY", s);
	}