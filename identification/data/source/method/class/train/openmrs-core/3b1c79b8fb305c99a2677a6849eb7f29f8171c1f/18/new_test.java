	@Test
	public void getAllPrivileges_shouldReturnAllPrivilegesInTheSystem() {
		executeDataSet(XML_FILENAME);
		List<Privilege> privileges = userService.getAllPrivileges();
		Assert.assertEquals(1, privileges.size());
	}