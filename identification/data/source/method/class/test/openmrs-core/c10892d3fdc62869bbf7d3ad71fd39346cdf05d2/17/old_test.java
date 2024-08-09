	@Test
	public void getAllLocationTags_shouldReturnAllLocationTagsIncludingRetired() {
		List<LocationTag> tags = Context.getLocationService().getAllLocationTags();
		
		Assert.assertEquals(6, tags.size());
	}