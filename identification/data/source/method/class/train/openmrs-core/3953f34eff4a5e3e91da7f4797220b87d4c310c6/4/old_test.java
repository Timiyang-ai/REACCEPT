	@Test
	public void saveConcept_shouldReturnTheConceptWithNewConceptIDIfCreatingNewConcept() {
		Concept c = new Concept();
		ConceptName fullySpecifiedName = new ConceptName("requires one name min", new Locale("fr", "CA"));
		c.addName(fullySpecifiedName);
		c.addDescription(new ConceptDescription("some description", null));
		c.setDatatype(new ConceptDatatype(1));
		c.setConceptClass(new ConceptClass(1));
		Concept savedC = Context.getConceptService().saveConcept(c);
		assertNotNull(savedC);
		assertTrue(savedC.getConceptId() > 0);
	}