	@Test
	public void hasPrivilege_shouldNotFailGivenNullParameter() {
		Role role = new Role();
		
		// test the null case
		role.hasPrivilege(null);
	}