	@Test
	public void getLocationsHavingAnyTag_shouldGetLocationsHavingAnyTag() {
		LocationService ls = Context.getLocationService();
		
		List<LocationTag> list1 = new ArrayList<>();
		list1.add(ls.getLocationTag(1));
		list1.add(ls.getLocationTag(2));
		
		List<LocationTag> list2 = new ArrayList<>();
		list2.add(ls.getLocationTag(3));
		list2.add(ls.getLocationTag(4));
		
		List<LocationTag> list3 = new ArrayList<>();
		list3.add(ls.getLocationTag(1));
		list3.add(ls.getLocationTag(2));
		list3.add(ls.getLocationTag(3));
		
		assertEquals(1, ls.getLocationsHavingAnyTag(list1).size());
		assertEquals(4, ls.getLocationsHavingAnyTag(list2).size());
		assertEquals(3, ls.getLocationsHavingAnyTag(list3).size());
	}