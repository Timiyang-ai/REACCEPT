	@Test
	public void getTrueConcept_shouldReturnTheTrueConcept() {
		createTrueFalseGlobalProperties();
		Assert.assertEquals(7, conceptService.getTrueConcept().getConceptId().intValue());
	}