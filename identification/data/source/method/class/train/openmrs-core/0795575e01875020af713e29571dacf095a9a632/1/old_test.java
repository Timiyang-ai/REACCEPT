	@Test
	public void saveUser_shouldUpdateUsersUsername() {
		User u = userService.getUserByUsername(ADMIN_USERNAME);
		assertNotNull("There needs to be a user with username 'admin' in the database", u);
		
		u.setUsername("admin2");
		userService.saveUser(u);
		
		User u2 = userService.getUserByUsername("admin2");
		
		assertEquals("The fetched user should equal the user we tried to update", u, u2);
	}