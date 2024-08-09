	@Test
	public void getConceptsByClass_shouldGetConceptsByClass() {				
		// replay
		List<Concept> actualConcepts = conceptService.getConceptsByClass(new ConceptClass(3));
		
		// verify
		Assert.assertThat(actualConcepts.size(), is(4));
		Assert.assertThat(actualConcepts, containsInAnyOrder(conceptService.getConcept(3), conceptService.getConcept(60), conceptService.getConcept(88), conceptService.getConcept(792)));
	}