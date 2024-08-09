@Test(expected = ContextAuthenticationException.class)
	public void authenticate_shouldNotAuthenticateGivenNullPasswordWhenPasswordInDatabaseIsNull() {
		dao.authenticate("nullpassword", null);
	}