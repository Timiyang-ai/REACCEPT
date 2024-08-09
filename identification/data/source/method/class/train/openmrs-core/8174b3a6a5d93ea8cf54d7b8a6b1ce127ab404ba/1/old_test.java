@Test
	@Verifies(value = "should allow different properties to have the same string with different case", method = "saveGlobalProperty(GlobalProperty)")
	public void saveGlobalProperty_shouldAllowDifferentPropertiesToHaveTheSameStringWithDifferentCase() throws Exception {
		executeDataSet("org/openmrs/api/include/AdministrationServiceTest-globalproperties.xml");
		
		// sanity check
		String orig = adminService.getGlobalProperty("another-global-property");
		Assert.assertEquals("anothervalue", orig);
		
		// should match current gp
		GlobalProperty gp = new GlobalProperty("ANOTher-global-property", "somethingelse");
		adminService.saveGlobalProperty(gp);
		String prop = adminService.getGlobalProperty("ANOTher-global-property", "boo");
		Assert.assertEquals("somethingelse", prop);
	}