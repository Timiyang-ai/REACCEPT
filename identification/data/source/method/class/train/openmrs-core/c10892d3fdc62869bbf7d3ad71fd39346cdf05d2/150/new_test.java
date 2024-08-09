	@Test
	public void retireLocation_shouldRetireLocationSuccessfully() {
		LocationService ls = Context.getLocationService();
		
		// Get all locations.
		List<Location> locationsBeforeRetired = ls.getAllLocations(true);
		List<Location> locationsNotRetiredBefore = ls.getAllLocations(false);
		
		// Get a non-retired location
		Location location = ls.getLocation(1);
		
		Location retiredLoc = ls.retireLocation(location, "Just Testing");
		
		// Get all locations again.
		List<Location> locationsAfterRetired = ls.getAllLocations(true);
		List<Location> locationsNotRetiredAfter = ls.getAllLocations(false);
		
		// make sure that all the values were filled in
		assertTrue(retiredLoc.getRetired());
		assertNotNull(retiredLoc.getDateRetired());
		assertEquals(Context.getAuthenticatedUser(), retiredLoc.getRetiredBy());
		assertEquals("Just Testing", retiredLoc.getRetireReason());
		
		// Both location lists that include retired should be equal in size and not order of elements.
		assertEquals(locationsBeforeRetired.size(), locationsAfterRetired.size());
		
		// Both location lists that do not include retired should not be the same.
		assertNotSame(locationsNotRetiredBefore, locationsNotRetiredAfter);
	}