	@Test
	public void getRole_shouldFetchRoleForGivenRoleName() {
		executeDataSet(XML_FILENAME);
		Role role = userService.getRole("Some Role");
		Assert.assertEquals("Some Role", role.getRole());
	}