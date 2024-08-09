	@Test
	public void getRootLocations_shouldReturnRootLocationsIncludingRetired() {
		List<Location> locations = Context.getLocationService().getRootLocations(true);
		
		Assert.assertEquals(3, locations.size());
	}