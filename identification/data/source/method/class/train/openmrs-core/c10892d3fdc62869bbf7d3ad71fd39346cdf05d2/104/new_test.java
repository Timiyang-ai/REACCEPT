	@Test
	public void unretireUser_shouldUnretireAndUnmarkAllAttributes() {
		User user = userService.getUser(501);
		userService.unretireUser(user);
		Assert.assertFalse(user.getRetired());
		Assert.assertNull(user.getDateRetired());
		Assert.assertNull(user.getRetiredBy());
		Assert.assertNull(user.getRetireReason());
	}