@Test
	@Verifies(value = "should return default location for the implementation", method = "getDefaultLocation()")
	public void getDefaultLocation_shouldReturnDefaultLocationForTheImplementation() throws Exception {
		LocationService ls = Context.getLocationService();
		AdministrationService as = Context.getAdministrationService();
		String defaultLocationName = as.getGlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME);
		
		// Test when default location is set
		as.saveGlobalProperty(new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME, "Test Location"));
		Location defaultLocation = ls.getDefaultLocation();
		Assert.assertNotNull(defaultLocation);
		Assert.assertEquals("Test Location", defaultLocation.getName());
		
		// Test when default location is not set
		as.purgeGlobalProperty(as.getGlobalPropertyByUuid(defaultLocationName));
		defaultLocation = ls.getDefaultLocation();
		Assert.assertNotNull(defaultLocation);
		Assert.assertEquals("Unknown Location", defaultLocation.getName());
	}