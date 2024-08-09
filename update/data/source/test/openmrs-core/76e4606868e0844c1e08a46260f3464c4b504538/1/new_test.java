@Test
	@Verifies(value = "should return default location for the implementation", method = "getDefaultLocation()")
	public void getDefaultLocation_shouldReturnDefaultLocationForTheImplementation() throws Exception {
		//set the global property for default location to something other than Unknown Location
		GlobalProperty gp = new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME,
		        "Test Parent Location", "Testing default Location");
		Context.getAdministrationService().saveGlobalProperty(gp);
		Assert.assertEquals("Test Parent Location", Context.getLocationService().getDefaultLocation().getName());
	}