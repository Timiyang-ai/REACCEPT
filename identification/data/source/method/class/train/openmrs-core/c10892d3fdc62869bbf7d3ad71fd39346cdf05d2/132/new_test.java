	@Test
	public void getAllLocations_shouldReturnAllLocationsIncludingRetired() {
		List<Location> locations = Context.getLocationService().getAllLocations();
		
		Assert.assertEquals(6, locations.size());
	}