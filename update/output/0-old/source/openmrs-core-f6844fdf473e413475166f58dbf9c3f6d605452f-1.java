@Test(expected = APIException.class)
	@Verifies(value = "should throw error when privilege is core privilege", method = "purgePrivilege(Privilege)")
	public void purgePrivilege_shouldThrowErrorWhenPrivilegeIsCorePrivilege() throws Exception {
		Context.getUserService().purgePrivilege(new Privilege(OpenmrsConstants.PRIV_ADD_COHORTS));
	}