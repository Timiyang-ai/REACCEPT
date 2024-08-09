	@Test
	public void getConceptStateConversionByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "6c72b064-506d-11de-80cb-001e378eb67e";
		ConceptStateConversion conceptStateConversion = Context.getProgramWorkflowService().getConceptStateConversionByUuid(
		    uuid);
		Assert.assertEquals(1, (int) conceptStateConversion.getConceptStateConversionId());
	}