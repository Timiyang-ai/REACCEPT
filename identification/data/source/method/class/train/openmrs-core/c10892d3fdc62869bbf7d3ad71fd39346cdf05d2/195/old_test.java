	@Test
	public void getAllGlobalProperties_shouldReturnAllGlobalPropertiesInTheDatabase() {
		int allGlobalPropertiesSize = adminService.getAllGlobalProperties().size();
		executeDataSet(ADMIN_INITIAL_DATA_XML);
		assertEquals(allGlobalPropertiesSize + 9, adminService.getAllGlobalProperties().size());
	}