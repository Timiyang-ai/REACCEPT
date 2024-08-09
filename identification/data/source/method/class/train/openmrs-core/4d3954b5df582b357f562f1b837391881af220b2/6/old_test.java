	@Test
	public void serialize_shouldReturnTheUuidOfTheObject() {
		OpenmrsObject location = new Location();
		String expectedUuid = "some uuid";
		location.setUuid(expectedUuid);
		BaseOpenmrsDatatype datatype = new MockLocationDatatype();
		Assert.assertEquals(expectedUuid, datatype.serialize(location));
	}