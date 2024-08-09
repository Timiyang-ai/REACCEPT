	@Test
	public void hasDuplicateUsername_shouldVerifyThatUsernameAndSystemIdIsUnique() {
		executeDataSet(XML_FILENAME);
		
		User user = new User();
		user.setSystemId("8-3");
		user.setUsername("a unique username");
		Assert.assertTrue(userService.hasDuplicateUsername(user));
		
		user = new User();
		user.setSystemId("a unique system id");
		user.setUsername("userWithSha512Hash");
		Assert.assertTrue(userService.hasDuplicateUsername(user));
	}