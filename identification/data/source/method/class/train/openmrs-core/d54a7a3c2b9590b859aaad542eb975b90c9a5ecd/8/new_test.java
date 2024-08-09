	@Test
	public void getAllVisitAttributeTypes_shouldReturnAllVisitAttributeTypesIncludingRetiredOnes() {
		executeDataSet(VISITS_ATTRIBUTES_XML);
		assertEquals(3, visitService.getAllVisitAttributeTypes().size());
	}