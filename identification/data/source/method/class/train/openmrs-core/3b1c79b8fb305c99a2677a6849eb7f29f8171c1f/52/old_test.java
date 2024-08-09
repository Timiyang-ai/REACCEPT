	@Test
	public void getPrivilegeByUuid_shouldFindObjectGivenValidUuid() {
		executeDataSet(XML_FILENAME);
		String uuid = "d979d066-15e6-467c-9d4b-cb575ef97f0f";
		Privilege privilege = userService.getPrivilegeByUuid(uuid);
		Assert.assertEquals("Some Privilege", privilege.getPrivilege());
	}