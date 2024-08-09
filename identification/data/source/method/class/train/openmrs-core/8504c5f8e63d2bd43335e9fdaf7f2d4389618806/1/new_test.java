	@Test
	public void getOrderableConcepts_shouldGetOrderableConcepts() {
		//In current data set order_type_map table contains conceptClass 1 and 3.
		// Using that adding two concepts to test the functionality
		ConceptService cs = Context.getConceptService();
		ConceptClass cc1 = cs.getConceptClass(1);
		ConceptClass cc3 = cs.getConceptClass(3);
		Locale locale = Locale.ENGLISH;
		ConceptDatatype dt = cs.getConceptDatatype(4);
		Concept c1 = new Concept();
		ConceptName cn1a = new ConceptName("ONE TERM", locale);
		c1.addName(cn1a);
		c1.addDescription(new ConceptDescription("some description",null));
		c1.setConceptClass(cc1);
		c1.setDatatype(dt);
		cs.saveConcept(c1);
		
		Concept c2 = new Concept();
		ConceptName cn2a = new ConceptName("ONE TO MANY", locale);
		c2.addName(cn2a);
		c2.addDescription(new ConceptDescription("some description",null));
		c2.setConceptClass(cc3);
		c2.setDatatype(dt);
		cs.saveConcept(c2);
		
		updateSearchIndex();
		
		List<ConceptSearchResult> conceptSearchResultList = Context.getConceptService().getOrderableConcepts("one",
		    Collections.singletonList(locale), true, null, null);
		assertEquals(2, conceptSearchResultList.size());
	}