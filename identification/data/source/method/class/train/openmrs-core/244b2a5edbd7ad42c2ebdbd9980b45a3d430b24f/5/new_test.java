	@Test
	public void getLocationAttributeByUuid_shouldGetTheLocationAttributeWithTheGivenUuid() {
		executeDataSet(LOC_ATTRIBUTE_DATA_XML);
		LocationService service = Context.getLocationService();
		Assert.assertEquals("2011-04-25", service.getLocationAttributeByUuid("3a2bdb18-6faa-11e0-8414-001e378eb67e")
		        .getValueReference());
	}