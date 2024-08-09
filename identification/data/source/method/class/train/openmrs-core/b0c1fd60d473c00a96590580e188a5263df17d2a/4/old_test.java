	@Test
	public void getLocationByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "f08ba64b-ea57-4a41-b33c-9dfc59b0c60a";
		Location location = Context.getLocationService().getLocationByUuid(uuid);
		Assert.assertEquals(1, (int) location.getLocationId());
	}