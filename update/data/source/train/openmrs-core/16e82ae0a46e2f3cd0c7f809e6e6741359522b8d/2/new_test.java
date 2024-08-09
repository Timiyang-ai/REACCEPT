@Test
	public void purgeGlobalProperties_shouldDeleteGlobalPropertiesFromDatabase() throws Exception {
		int originalSize = adminService.getAllGlobalProperties().size();
		
		List<GlobalProperty> props = new ArrayList<GlobalProperty>();
		props.add(new GlobalProperty("a.property.key", "something"));
		props.add(new GlobalProperty("a.property.KEY", "somethingelse"));
		adminService.saveGlobalProperties(props);
		int afterSaveSize = adminService.getAllGlobalProperties().size();
		
		Assert.assertEquals(originalSize + 2, afterSaveSize);
		
		adminService.purgeGlobalProperties(props);
		int afterPurgeSize = adminService.getAllGlobalProperties().size();
		
		Assert.assertEquals(originalSize, afterPurgeSize);
	}