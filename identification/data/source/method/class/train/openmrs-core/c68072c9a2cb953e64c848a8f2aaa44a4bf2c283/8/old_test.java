	@Test
	public void findPossibleValues_shouldReturnListOfConceptsFromMatchingResults() throws Exception {
		Concept concept = new Concept(1);
		concept.addName(new ConceptName("findPossibleValueTest", Context.getLocale()));
		concept.addDescription(new ConceptDescription("en desc", Context.getLocale()));
		concept.setDatatype(new ConceptDatatype(1));
		concept.setConceptClass(new ConceptClass(1));
		
		List<Concept> expectedConcepts = new ArrayList<>();
		
		concept = Context.getConceptService().saveConcept(concept);
		expectedConcepts.add(concept);
		Concept newConcept = new Concept(2);
		newConcept.addName(new ConceptName("New Test Concept", Context.getLocale()));
		newConcept.addDescription(new ConceptDescription("new desc", Context.getLocale()));
		newConcept.setDatatype(new ConceptDatatype(1));
		newConcept.setConceptClass(new ConceptClass(1));
		newConcept = Context.getConceptService().saveConcept(newConcept);
		
		Context.updateSearchIndexForType(ConceptName.class);
		
		List<Concept> resultConcepts = newConcept.findPossibleValues("findPossibleValueTest");
		Assert.assertEquals(expectedConcepts, resultConcepts);
	}