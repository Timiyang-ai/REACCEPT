	@Test
	public void purgeGlobalProperties_shouldDeleteGlobalPropertiesFromDatabase() {
		int originalSize = adminService.getAllGlobalProperties().size();
		
		List<GlobalProperty> props = new ArrayList<>();
		props.add(new GlobalProperty("a.property.key", "something"));
		props.add(new GlobalProperty("a.property.KEY", "somethingelse"));
		adminService.saveGlobalProperties(props);
		int afterSaveSize = adminService.getAllGlobalProperties().size();
		
		assertEquals(originalSize + 1, afterSaveSize);
		
		adminService.purgeGlobalProperties(props);
		int afterPurgeSize = adminService.getAllGlobalProperties().size();
		
		assertEquals(originalSize, afterPurgeSize);
	}