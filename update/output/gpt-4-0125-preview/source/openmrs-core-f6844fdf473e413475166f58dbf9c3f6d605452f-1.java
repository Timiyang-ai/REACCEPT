@Test(expected = APIException.class)
	@Verifies(value = "should throw error when privilege is core privilege", method = "purgePrivilege(Privilege)")
	public void purgePrivilege_shouldThrowErrorWhenPrivilegeIsCorePrivilege() throws Exception {
		Context.getUserService().purgePrivilege(new Privilege(PrivilegeConstants.ADD_COHORTS));
	}