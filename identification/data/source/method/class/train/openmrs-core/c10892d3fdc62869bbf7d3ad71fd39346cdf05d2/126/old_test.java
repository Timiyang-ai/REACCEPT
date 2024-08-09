	@Test
	public void retireUser_shouldRetireUserAndSetAttributes() {
		User user = userService.getUser(502);
		userService.retireUser(user, "because");
		Assert.assertTrue(user.getRetired());
		Assert.assertNotNull(user.getDateRetired());
		Assert.assertNotNull(user.getRetiredBy());
		Assert.assertEquals("because", user.getRetireReason());
	}