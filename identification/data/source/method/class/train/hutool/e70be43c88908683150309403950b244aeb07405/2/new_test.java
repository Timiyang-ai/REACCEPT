	@Test
	public void getTest() {
		String resultGet = ReUtil.get("\\w{2}", content, 0);
		Assert.assertEquals("ZZ", resultGet);
	}