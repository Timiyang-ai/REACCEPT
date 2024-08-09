	@Test
	public void getUserByUsername_shouldGetUserByUsername() {
		User user = userService.getUserByUsername(ADMIN_USERNAME);

		assertNotNull("username not found " + ADMIN_USERNAME, user);
	}