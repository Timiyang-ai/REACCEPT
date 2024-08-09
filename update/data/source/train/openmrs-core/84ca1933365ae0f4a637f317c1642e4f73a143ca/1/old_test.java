@Test
	@Verifies(value = "should not contains any duplicate users", method = "getAllUsers()")
	public void getAllUsers_shouldNotContainsAnyDuplicateUsers() throws Exception {
		executeDataSet(XML_FILENAME);
		List<User> users = Context.getUserService().getAllUsers();
		Assert.assertEquals(10, users.size());
		// TODO Need to test with duplicate data in the dataset (not sure if that's possible)
		
	}