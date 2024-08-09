	@Test
	public void addTest() {
		Float a = 3.15f;
		Double b = 4.22;
		double result = NumberUtil.add(a, b).doubleValue();
		Assert.assertEquals(7.37, result, 2);
	}