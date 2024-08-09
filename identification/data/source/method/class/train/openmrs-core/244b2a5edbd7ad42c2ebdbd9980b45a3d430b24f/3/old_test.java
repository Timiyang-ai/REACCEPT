	@Test
	public void getLocationAttributeType_shouldReturnTheLocationAttributeTypeWithTheGivenId() {
		executeDataSet(LOC_ATTRIBUTE_DATA_XML);
		Assert.assertEquals("Audit Date", Context.getLocationService().getLocationAttributeType(1).getName());
	}