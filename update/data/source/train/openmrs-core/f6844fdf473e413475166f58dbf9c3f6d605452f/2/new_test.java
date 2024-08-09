@Test(expected = APIException.class)
	@Verifies(value = "should throw error when role is a core role", method = "purgeRole(Role)")
	public void purgeRole_shouldThrowErrorWhenRoleIsACoreRole() throws Exception {
		Role role = new Role(RoleConstants.ANONYMOUS);
		Context.getUserService().purgeRole(role);
	}