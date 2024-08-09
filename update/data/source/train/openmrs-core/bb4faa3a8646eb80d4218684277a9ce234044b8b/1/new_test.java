@Test(expected = ContextAuthenticationException.class)
	public void authenticate_shouldNotAuthenticateWithNullUsernameAndPassword() {
		Context.authenticate((String) null, (String) null);
	}