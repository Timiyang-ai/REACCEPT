	@Test
	public void getFalseConcept_shouldReturnTheFalseConcept() {
		createTrueFalseGlobalProperties();
		Assert.assertEquals(8, conceptService.getFalseConcept().getConceptId().intValue());
	}