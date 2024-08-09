@Test(expected = ContextAuthenticationException.class)
	public void authenticate_shouldNotAuthenticateGivenNullPasswordWhenPasswordInDatabaseIsNull() throws Exception {
		dao.authenticate("nullpassword", null);
	}