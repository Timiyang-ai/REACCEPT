	@Test
	public void getVisitAttributeTypeByUuid_shouldReturnTheVisitAttributeTypeWithTheGivenUuid() {
		executeDataSet(VISITS_ATTRIBUTES_XML);
		assertEquals("Audit Date", visitService.getVisitAttributeTypeByUuid("9516cc50-6f9f-11e0-8414-001e378eb67e")
		        .getName());
	}