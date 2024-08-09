@Test(expected = InvalidCharactersPasswordException.class)
	@Verifies(value = "should fail without upper and lower case password by default", method = "validatePassword(String,String,String)")
	public void validatePassword_shouldFailWithoutUpperAndLowerCasePasswordByDefault() throws Exception {
		OpenmrsUtil.validatePassword("admin", "test0nl1", "1-8");
	}