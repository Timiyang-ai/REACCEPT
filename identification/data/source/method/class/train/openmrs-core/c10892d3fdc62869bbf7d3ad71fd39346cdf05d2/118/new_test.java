	@Test
	public void getConceptsByConceptSet_shouldReturnAllConceptsInSet() {
		
		executeDataSet(GET_CONCEPTS_BY_SET_XML);
		
		Concept concept = conceptService.getConcept(1);
		
		List<Concept> conceptSet = conceptService.getConceptsByConceptSet(concept);
		
		assertThat(conceptSet, containsInAnyOrder(hasId(2), hasId(3), hasId(4), hasId(5), hasId(6)));
	}