@Test
	@Verifies(value = "should return true for any privilegeName if super user", method = "hasPrivilege(String)")
	public void hasPrivilege_shouldReturnTrueForAnyPrivilegeNameIfSuperUser() throws Exception {
		// check super user "super" status
		Role role = new Role(OpenmrsConstants.SUPERUSER_ROLE);
		
		assertTrue("Super users are super special and should have all privileges", role
		        .hasPrivilege("Some weird privilege name that shouldn't be there"));
	}