	@Test
	public void getLocationTagByName_shouldGetLocationTagByName() {
		LocationService ls = Context.getLocationService();
		
		// Existing tag
		assertEquals(ls.getLocationTag(1), ls.getLocationTagByName("General Hospital"));
		
		// Nonexistant tag
		assertEquals(null, ls.getLocationTagByName("random"));
	}