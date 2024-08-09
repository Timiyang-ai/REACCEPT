	@Test(expected = InvalidCharactersPasswordException.class)
	public void validatePassword_shouldFailWithDigitOnlyPasswordByDefault() {
		OpenmrsUtil.validatePassword("admin", "12345678", "1-8");
	}