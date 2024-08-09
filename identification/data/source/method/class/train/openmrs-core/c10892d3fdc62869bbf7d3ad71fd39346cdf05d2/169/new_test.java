	@Test
	public void getLocationsByTag_shouldGetLocationsByTag() {
		LocationService ls = Context.getLocationService();
		
		assertEquals(1, ls.getLocationsByTag(ls.getLocationTag(1)).size());
		assertEquals(2, ls.getLocationsByTag(ls.getLocationTag(3)).size());
		assertEquals(4, ls.getLocationsByTag(ls.getLocationTag(4)).size());
	}