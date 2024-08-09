	@Test
	public void purgeUser_shouldDeleteGivenUser() {
		User user = userService.getUser(502);
		userService.purgeUser(user);
		Assert.assertNull(userService.getUser(2));
	}