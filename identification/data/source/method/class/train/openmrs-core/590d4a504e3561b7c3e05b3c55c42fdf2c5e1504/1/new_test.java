	@Test
	public void setImplementationId_shouldNotFailIfGivenImplementationIdIsNull() {
		// save a null impl id. no exception thrown
		adminService.setImplementationId(null);
		ImplementationId afterNull = adminService.getImplementationId();
		assertNull("There shouldn't be an impl id defined after setting a null impl id", afterNull);
	}