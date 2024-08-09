	@Test
	public void isMobileTest() {
		boolean m1 = Validator.isMobile("13900221432");
		Assert.assertTrue(m1);
		boolean m2 = Validator.isMobile("015100221432");
		Assert.assertTrue(m2);
		boolean m3 = Validator.isMobile("+8618600221432");
		Assert.assertTrue(m3);
	}