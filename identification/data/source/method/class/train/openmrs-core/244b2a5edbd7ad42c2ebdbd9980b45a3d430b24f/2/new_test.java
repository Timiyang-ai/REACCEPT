	@Test
	public void getLocationAttributeTypeByUuid_shouldReturnTheLocationAttributeTypeWithTheGivenUuid() {
		executeDataSet(LOC_ATTRIBUTE_DATA_XML);
		Assert.assertEquals("Audit Date", Context.getLocationService().getLocationAttributeTypeByUuid(
		    "9516cc50-6f9f-11e0-8414-001e378eb67e").getName());
	}