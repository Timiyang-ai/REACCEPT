	@Test(expected = ContextAuthenticationException.class)
	public void authenticate_shouldNotAuthenticateWithNullPassword() {
		Context.authenticate("some username", null);
	}