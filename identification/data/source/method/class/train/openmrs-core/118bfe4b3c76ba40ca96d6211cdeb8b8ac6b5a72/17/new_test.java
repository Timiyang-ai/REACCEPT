	@Test
	public void getUserByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "c1d8f5c2-e131-11de-babe-001e378eb67e";
		User user = userService.getUserByUuid(uuid);
		Assert.assertEquals(501, (int) user.getUserId());
	}