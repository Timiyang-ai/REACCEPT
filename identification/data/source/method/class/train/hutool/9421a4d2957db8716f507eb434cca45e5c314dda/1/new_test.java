	@Test
	public void monthTest() {
		int month = DateUtil.parse("2017-07-01").month();
		Assert.assertEquals(6, month);
	}