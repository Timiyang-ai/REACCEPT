	@Test
	public void isSuperUser_shouldBeSuperUser() {
		user.addRole(new Role(RoleConstants.SUPERUSER));
		assertTrue(user.isSuperUser());
	}