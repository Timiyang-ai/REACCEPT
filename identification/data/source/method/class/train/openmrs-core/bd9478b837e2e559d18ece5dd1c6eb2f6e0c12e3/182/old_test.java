	@Test
	public void getPrivilege_shouldFetchPrivilegeForGivenName() {
		executeDataSet(XML_FILENAME);
		Privilege privilege = userService.getPrivilege("Some Privilege");
		Assert.assertEquals("Some Privilege", privilege.getPrivilege());
	}