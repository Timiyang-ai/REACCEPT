	@Test
	public void isUserNameValid_shouldValidateUsernameWithOnlyAlphaNumerics() {
		Assert.assertTrue(validator.isUserNameValid("AB"));
	}