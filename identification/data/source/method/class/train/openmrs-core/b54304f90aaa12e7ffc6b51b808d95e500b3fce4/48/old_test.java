	@Test
	public void getDefaultLocation_shouldReturnTheUpdatedDefaultLocationWhenTheValueOfTheGlobalPropertyIsChanged()
	{
		//sanity check
		Assert.assertEquals("Unknown Location", LocationUtility.getDefaultLocation().getName());
		GlobalProperty gp = new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME, "Xanadu", "Testing");
		Context.getAdministrationService().saveGlobalProperty(gp);
		Assert.assertEquals("Xanadu", LocationUtility.getDefaultLocation().getName());
	}