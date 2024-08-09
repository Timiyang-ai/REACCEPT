	@Test
	public void purgeLocation_shouldDeleteLocationSuccessfully() {
		
		LocationService ls = Context.getLocationService();
		
		// fetch the encounter to delete from the db
		Location locationToDelete = ls.getLocation(4);
		
		ls.purgeLocation(locationToDelete);
		
		// try to refetch the location. should get a null object
		Location l = ls.getLocation(locationToDelete.getLocationId());
		
		assertNull("We shouldn't find the location after deletion", l);
	}