@Test
	public void getAllUsers_shouldNotContainsAnyDuplicateUsers() {
		executeDataSet(XML_FILENAME);
		List<User> users = userService.getAllUsers();
		Assert.assertEquals(12, users.size());
		// TODO Need to test with duplicate data in the dataset (not sure if that's possible)
		
	}