@Test
	public void authenticate_shouldAuthenticateUserWithUsernameAndPassword() {
		// replay
		Context.logout();
		Context.authenticate("admin", "test");
		String userUuid = Context.getAuthenticatedUser().getUuid();
		
		Context.logout();
		Authenticated authenticated = Context.authenticate(new UsernamePasswordAuthenticationScheme(), new UsernamePasswordCredentials("admin", "test"));
		
		// verif
		Assert.assertEquals(UsernamePasswordCredentials.SCHEME, authenticated.getAuthenticationScheme());
		Assert.assertEquals(userUuid, Context.getAuthenticatedUser().getUuid());
	}