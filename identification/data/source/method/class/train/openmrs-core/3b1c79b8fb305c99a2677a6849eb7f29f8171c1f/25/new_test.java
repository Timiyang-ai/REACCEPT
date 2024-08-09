	@Test
	public void getGlobalPropertiesByPrefix_shouldReturnAllRelevantGlobalPropertiesInTheDatabase() {
		executeDataSet("org/openmrs/api/include/AdministrationServiceTest-globalproperties.xml");
		
		List<GlobalProperty> properties = adminService.getGlobalPropertiesByPrefix("fake.module.");
		
		for (GlobalProperty property : properties) {
			assertTrue(property.getProperty().startsWith("fake.module."));
			assertTrue(property.getPropertyValue().startsWith("correct-value"));
		}
	}