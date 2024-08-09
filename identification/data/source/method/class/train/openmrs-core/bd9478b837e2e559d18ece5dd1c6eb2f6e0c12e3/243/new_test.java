	@Test
	public void changePassword_shouldBeAbleToUpdatePasswordMultipleTimes() {
		User u = userService.getUserByUsername(ADMIN_USERNAME);
		assertNotNull("There needs to be a user with username 'admin' in the database", u);
		
		userService.changePassword("test", "Tester12");
		userService.changePassword("Tester12", "Tester13");
	}