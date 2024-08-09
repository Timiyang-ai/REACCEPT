	@Test
	public void updateGlobalProperty_shouldUpdateGlobalPropertyInDatabase() {
		executeDataSet("org/openmrs/api/include/AdministrationServiceTest-globalproperties.xml");
		
		String propertyValue = adminService.getGlobalProperty("a_valid_gp_key");
		assertEquals("correct-value", propertyValue);
		
		adminService.updateGlobalProperty("a_valid_gp_key", "new-value");
		
		String newValue = adminService.getGlobalProperty("a_valid_gp_key");
		assertEquals("new-value", newValue);
	}