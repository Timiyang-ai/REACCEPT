@Test
	@Verifies(value = "should return default location for the implementation", method = "getDefaultLocation()")
	public void getDefaultLocation_shouldReturnDefaultLocationForTheImplementation() throws Exception {
		Assert.assertNotNull(Context.getLocationService().getDefaultLocation());
	}