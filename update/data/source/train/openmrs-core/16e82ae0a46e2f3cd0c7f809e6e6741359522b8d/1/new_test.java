@Test
	@Verifies(value = "should save properties with case difference only", method = "saveGlobalProperties(List<QGlobalProperty;>)")
	public void saveGlobalProperties_shouldSavePropertiesWithCaseDifferenceOnly() throws Exception {
		int originalSize = adminService.getAllGlobalProperties().size();
		
		List<GlobalProperty> props = new ArrayList<GlobalProperty>();
		props.add(new GlobalProperty("a.property.key", "something"));
		props.add(new GlobalProperty("a.property.KEY", "somethingelse"));
		adminService.saveGlobalProperties(props);
		
		// make sure that we now have two properties
		props = adminService.getAllGlobalProperties();
		Assert.assertEquals(originalSize + 2, props.size());
	}