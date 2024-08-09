	@Test
	public void getFormsContainingConcept_shouldGetAllFormsForConcept() {
		Concept concept = Context.getConceptService().getConcept(3);
		
		assertEquals(1, Context.getFormService().getFormsContainingConcept(concept).size());
	}