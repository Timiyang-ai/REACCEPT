	@Test
	public void getAllLocationAttributeTypes_shouldReturnAllLocationAttributeTypesIncludingRetiredOnes() {
		executeDataSet(LOC_ATTRIBUTE_DATA_XML);
		Assert.assertEquals(2, Context.getLocationService().getAllLocationAttributeTypes().size());
	}