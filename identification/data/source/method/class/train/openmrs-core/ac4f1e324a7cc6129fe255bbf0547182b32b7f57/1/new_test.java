	@Test
	public void authenticate_shouldAuthenticateGivenUsernameAndPassword() {
		User u = dao.authenticate("admin", "test");
		Assert.assertEquals("Should be the admin user", "admin", u.getUsername());
	}