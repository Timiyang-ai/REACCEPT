	@Test
	public void purgeLocationTag_shouldDeleteLocationTag() {
		LocationService ls = Context.getLocationService();
		
		// Fetch the encounter to delete from the db
		LocationTag tag = ls.getLocationTag(5);
		
		ls.purgeLocationTag(tag);
		
		// Try to refetch the location. should get a null object
		LocationTag t = ls.getLocationTag(tag.getLocationTagId());
		
		assertNull("We shouldn't find the tag after deletion", t);
	}