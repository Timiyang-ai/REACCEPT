	@Test
	public void isNumberTest() {
		Assert.assertTrue(Validator.isNumber("45345365465"));
		Assert.assertTrue(Validator.isNumber("0004545435"));
		Assert.assertTrue(Validator.isNumber("5.222"));
		Assert.assertTrue(Validator.isNumber("0.33323"));
	}