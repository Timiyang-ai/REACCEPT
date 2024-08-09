@Test
	public void getLocationsHavingAnyTag_shouldReturnEmptyListWhenGivenAnEmptyTagList() {
		LocationService ls = Context.getLocationService();
		Assert.assertEquals(0, ls.getLocationsHavingAnyTag(new ArrayList<LocationTag>()).size());
	}