	@Test
	public void getAllUsers_shouldFetchAllUsersInTheSystem() {
		List<User> users = userService.getAllUsers();
		Assert.assertEquals(3, users.size());
	}