	@Test
	public void authenticateAsSuperUser_shouldReturnFalseIfGivenInvalidCredentials() throws ServletException {
		Assert.assertFalse(new UpdateFilter().authenticateAsSuperUser("a-bad-username", "a-bad-password"));
	}