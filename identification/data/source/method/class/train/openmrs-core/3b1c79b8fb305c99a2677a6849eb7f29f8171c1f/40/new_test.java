	@Test
	public void getAllRoles_shouldReturnAllRolesInTheSystem() {
		executeDataSet(XML_FILENAME);
		
		List<Role> roles = userService.getAllRoles();
		Assert.assertEquals(7, roles.size());
	}