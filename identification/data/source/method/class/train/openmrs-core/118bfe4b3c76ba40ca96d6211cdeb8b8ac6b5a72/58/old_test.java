	@Test
	public void getConceptSetByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "1a111827-639f-4cb4-961f-1e025bf88d90";
		ConceptSet conceptSet = Context.getConceptService().getConceptSetByUuid(uuid);
		Assert.assertEquals(1, (int) conceptSet.getConceptSetId());
	}