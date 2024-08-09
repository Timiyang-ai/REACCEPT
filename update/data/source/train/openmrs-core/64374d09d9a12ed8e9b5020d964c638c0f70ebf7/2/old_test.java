@Test
	@Verifies(value = "should add location tag to location", method = "saveLocation(Location)")
	public void saveLocation_shouldAddLocationTagToLocation() throws Exception {
		LocationService ls = Context.getLocationService();
		
		// First, create a new Location
		Location location = new Location();
		location.setName("name");
		ls.saveLocation(location);
		
		// Create a tag
		LocationTag tag = new LocationTag();
		tag.setName("tag name");
		ls.saveLocationTag(tag);
		
		// Add tag to location
		location.addTag(tag);
		ls.saveLocation(location);
		
		Location newSavedLocation = ls.getLocation(location.getLocationId());
		
		// Saved parent location should have tags
		assertNotNull("newSavedLocation.tags must be not null", newSavedLocation.getTags());
		
		// Saved parent location should have exactly 1 tag
		assertEquals(1, newSavedLocation.getTags().size());
		
		// Tag must be the previously added object
		assertTrue("Tag must be the previously added tag", newSavedLocation.getTags().iterator().next().equals(tag));
	}