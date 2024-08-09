	@Test
	public void getSystemVariables_shouldReturnAllRegisteredSystemVariables() {
		// The method implementation adds 11 system variables
		assertEquals(11, adminService.getSystemVariables().size());
	}