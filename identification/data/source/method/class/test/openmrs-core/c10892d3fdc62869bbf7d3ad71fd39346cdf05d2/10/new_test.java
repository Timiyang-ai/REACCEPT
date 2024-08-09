	@Test
	public void purgeGlobalProperty_shouldDeleteGlobalPropertyFromDatabase() {
		executeDataSet(ADMIN_INITIAL_DATA_XML);
		int allGlobalPropertiesSize = adminService.getAllGlobalProperties().size();
		adminService.purgeGlobalProperty(adminService.getGlobalPropertyObject("a_valid_gp_key"));
		assertEquals(allGlobalPropertiesSize -1, adminService.getAllGlobalProperties().size());
	}