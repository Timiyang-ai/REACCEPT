	@Test
	public void upperFirstTest() {
		StringBuilder sb = new StringBuilder("KEY");
		String s = StrUtil.upperFirst(sb);
		Assert.assertEquals(s, sb.toString());
	}