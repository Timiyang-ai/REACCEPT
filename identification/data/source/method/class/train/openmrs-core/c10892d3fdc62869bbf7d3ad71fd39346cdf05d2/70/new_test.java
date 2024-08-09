	@Test
	public void saveRole_shouldSaveGivenRoleToTheDatabase() {
		Role role = new Role("new role", "new desc");
		userService.saveRole(role);
		
		Assert.assertNotNull(userService.getRole("new role"));
		
	}