	@Test
	public void getLocationsHavingAllTags_shouldGetLocationsHavingAllTags() {
		List<LocationTag> list1 = new ArrayList<>();
		list1.add(dao.getLocationTag(1));
		list1.add(dao.getLocationTag(2));
		
		List<LocationTag> list2 = new ArrayList<>();
		list2.add(dao.getLocationTag(3));
		list2.add(dao.getLocationTag(4));
		
		List<LocationTag> list3 = new ArrayList<>();
		list3.add(dao.getLocationTag(1));
		list3.add(dao.getLocationTag(2));
		list3.add(dao.getLocationTag(3));
		list3.add(dao.getLocationTag(4));
		
		List<LocationTag> list4 = new ArrayList<>();
		list4.add(dao.getLocationTag(4));
		
		assertEquals(1, dao.getLocationsHavingAllTags(list1).size());
		assertEquals(2, dao.getLocationsHavingAllTags(list2).size());
		assertEquals(0, dao.getLocationsHavingAllTags(list3).size());
		assertEquals(4, dao.getLocationsHavingAllTags(list4).size());
	}