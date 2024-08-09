	@Test
	public void getUsersByRole_shouldFetchUsersAssignedGivenRole() {
		executeDataSet(XML_FILENAME);
		
		Assert.assertEquals(2, userService.getUsersByRole(new Role("Some Role")).size());
	}