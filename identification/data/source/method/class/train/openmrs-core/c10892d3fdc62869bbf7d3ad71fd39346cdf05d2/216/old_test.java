	@Test
	public void saveLocationTag_shouldCreateLocationTagSuccessfully() {
		LocationTag tag = new LocationTag();
		
		tag.setName("testing");
		tag.setDescription("desc");
		
		LocationService ls = Context.getLocationService();
		ls.saveLocationTag(tag);
		
		LocationTag newSavedTag = ls.getLocationTag(tag.getLocationTagId());
		
		assertNotNull("The saved tag should have an id now", tag.getLocationTagId());
		assertNotNull("We should get back a tag", newSavedTag);
		assertTrue("The created tag needs to equal the pojo location", tag.equals(newSavedTag));
	}