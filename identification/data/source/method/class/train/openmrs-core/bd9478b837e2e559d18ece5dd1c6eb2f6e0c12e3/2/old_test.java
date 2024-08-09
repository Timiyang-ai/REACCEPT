	@Test
	public void getUsers_shouldMatchSearchToFamilyName2() {
		executeDataSet("org/openmrs/api/include/PersonServiceTest-extranames.xml");

		List<User> users = userService.getUsers("Johnson", null, false);
		Assert.assertEquals(3, users.size());
		Assert.assertTrue(containsId(users, 2));
		Assert.assertTrue(containsId(users, 4));
		Assert.assertTrue(containsId(users, 5));
	}