	@Test
	public void getImplementationId_shouldReturnNullIfNoImplementationIdIsDefinedYet() {
		executeDataSet(ADMIN_INITIAL_DATA_XML);
		assertNull(adminService.getImplementationId());
	}