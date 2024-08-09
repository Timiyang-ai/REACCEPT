	@Test
	public void getUser_shouldFetchUserWithGivenUserId() {
		User user = userService.getUser(501);
		Assert.assertEquals(501, user.getUserId().intValue());
	}