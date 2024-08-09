	@Test
	public void getConceptComplex_shouldReturnAConceptComplexObject() {
		executeDataSet("org/openmrs/api/include/ObsServiceTest-complex.xml");
		ConceptComplex concept = Context.getConceptService().getConceptComplex(8473);
		Assert.assertNotNull(concept);
	}