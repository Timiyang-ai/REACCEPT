	@Test
	public void isEmailTest() {
		boolean email = Validator.isEmail("abc_cde@163.com");
		Assert.assertTrue(email);
		boolean email1 = Validator.isEmail("abc_%cde@163.com");
		Assert.assertTrue(email1);
		boolean email2 = Validator.isEmail("abc_%cde@aaa.c");
		Assert.assertTrue(email2);
		boolean email3 = Validator.isEmail("xiaolei.lu@aaa.b");
		Assert.assertTrue(email3);
	}