	@Test
	public void getConceptsByConceptSource_shouldReturnAListOfConceptMapsIfConceptMappingsFound() {
		List<ConceptMap> list = conceptService
		        .getConceptMappingsToSource(conceptService.getConceptSourceByName("SNOMED CT"));
		assertEquals(2, list.size());
	}