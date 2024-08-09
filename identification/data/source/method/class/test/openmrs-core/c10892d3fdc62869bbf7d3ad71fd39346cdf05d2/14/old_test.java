	@Test
	public void getUsersByPerson_shouldFetchAllAccountsForAPersonWhenIncludeRetiredIsTrue() {
		executeDataSet(XML_FILENAME);
		Person person = new Person(5508);
		List<User> users = userService.getUsersByPerson(person, true);
		Assert.assertEquals(3, users.size());
	}