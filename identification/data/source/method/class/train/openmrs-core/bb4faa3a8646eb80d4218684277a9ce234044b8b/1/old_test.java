@Test(expected = ContextAuthenticationException.class)
	public void authenticate_shouldNotAuthenticateWithNullUsernameAndPassword() {
		Context.authenticate(null, null);
	}