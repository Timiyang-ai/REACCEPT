	@Test
	public void getLocationsHavingAllTags_shouldGetLocationsHavingAllTags() {
		LocationService ls = Context.getLocationService();
		
		List<LocationTag> list1 = new ArrayList<>();
		list1.add(ls.getLocationTag(1));
		list1.add(ls.getLocationTag(2));
		
		assertEquals(1, ls.getLocationsHavingAllTags(list1).size());
	}