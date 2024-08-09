	@Test
	public void saveGlobalProperty_shouldCreateGlobalPropertyInDatabase() {
		executeDataSet(ADMIN_INITIAL_DATA_XML);
		
		adminService.saveGlobalProperty(new GlobalProperty("detectHiddenSkill", "100"));
		assertNotNull(adminService.getGlobalProperty("detectHiddenSkill"));
	}