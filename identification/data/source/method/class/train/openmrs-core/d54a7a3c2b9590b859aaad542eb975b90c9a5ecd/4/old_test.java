	@Test
	public void getVisitAttributeType_shouldReturnTheVisitAttributeTypeWithTheGivenId() {
		executeDataSet(VISITS_ATTRIBUTES_XML);
		assertEquals("Audit Date", visitService.getVisitAttributeType(1).getName());
	}