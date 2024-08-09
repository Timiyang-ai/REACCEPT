	@Test
	public void getVisitAttributeByUuid_shouldGetTheVisitAttributeWithTheGivenUuid() {
		executeDataSet(VISITS_ATTRIBUTES_XML);
		assertEquals("2011-04-25", visitService.getVisitAttributeByUuid("3a2bdb18-6faa-11e0-8414-001e378eb67e")
		        .getValueReference());
	}