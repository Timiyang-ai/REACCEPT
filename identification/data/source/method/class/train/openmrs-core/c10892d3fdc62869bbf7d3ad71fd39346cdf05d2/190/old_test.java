	@Test
	public void getLocation_shouldReturnNullWhenNoLocationMatchGivenLocationId() {
		Assert.assertNull(Context.getLocationService().getLocation(1337));
	}