	@Test
	public void divTest() {
		double result = NumberUtil.div(0, 1);
		Assert.assertEquals(0.0, result, 0);
	}