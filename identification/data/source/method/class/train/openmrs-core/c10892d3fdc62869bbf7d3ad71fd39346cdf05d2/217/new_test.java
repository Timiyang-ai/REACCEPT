	@Test
	public void getLocationTags_shouldReturnEmptyListWhenNoLocationTagMatchGivenSearchString() {
		Assert.assertEquals(0, Context.getLocationService().getLocationTags("!!!").size());
	}