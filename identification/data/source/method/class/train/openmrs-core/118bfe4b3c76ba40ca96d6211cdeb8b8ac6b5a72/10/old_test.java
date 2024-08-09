	@Test
	public void getConceptNumericByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "c607c80f-1ea9-4da3-bb88-6276ce8868dd";
		ConceptNumeric conceptNumeric = Context.getConceptService().getConceptNumericByUuid(uuid);
		Assert.assertEquals(5089, (int) conceptNumeric.getConceptId());
	}