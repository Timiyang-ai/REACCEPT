	@Test
	public void getLocations_shouldReturnEmptyListWhenNoLocationMatchTheNameFragment() {
		Assert.assertEquals(0, Context.getLocationService().getLocations("Mansion").size());
	}