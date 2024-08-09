@Test
	@Verifies(value = "should match search to familyName2", method = "getUsers(String,List,boolean)")
	public void getUsers_shouldMatchSearchToFamilyName2() throws Exception {
		executeDataSet("org/openmrs/api/include/PersonServiceTest-extranames.xml");
		
		List<User> users = Context.getUserService().getUsers("Johnson", null, false);
		Assert.assertEquals(3, users.size());
		Assert.assertTrue(users.contains(new User(2)));
		Assert.assertTrue(users.contains(new User(4)));
		Assert.assertTrue(users.contains(new User(5)));
	}