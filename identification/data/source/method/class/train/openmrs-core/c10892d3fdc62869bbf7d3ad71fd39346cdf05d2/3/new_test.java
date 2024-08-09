	@Test
	public void getLocationTag_shouldReturnNullWhenNoLocationTagMatchGivenId() {
		Assert.assertNull(Context.getLocationService().getLocationTag(9999));
	}