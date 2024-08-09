@Test
	public void authenticate_shouldAuthenticateUserWithUsernameAndPassword() {
		// replay
		Context.logout();
		Context.authenticate("admin", "test");
		
		// verif
		Assert.assertEquals("admin", Context.getAuthenticatedUser().getUsername());
	}