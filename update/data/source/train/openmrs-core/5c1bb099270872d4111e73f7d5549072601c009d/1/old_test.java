@Test(expected = InvalidCharactersPasswordException.class)
	@Verifies(value = "should fail without upper case char password", method = "validatePassword(String,String,String)")
	public void validatePassword_shouldFailWithoutUpperCaseCharPassword() throws Exception {
		OpenmrsUtil.validatePassword("admin", "test0nl1", "1-8");
	}