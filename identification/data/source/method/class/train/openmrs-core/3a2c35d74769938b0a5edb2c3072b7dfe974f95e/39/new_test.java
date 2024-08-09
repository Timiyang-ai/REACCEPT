	@Test
	public void getConceptsByName_shouldReturnConceptsForAllCountriesAndGlobalLanguageGivenLanguageOnlyLocale()
	{
		//given
		String name = "Concept";
		Concept concept1 = new Concept();
		concept1.addName(new ConceptName(name, new Locale("en", "US")));
		concept1.addDescription(new ConceptDescription("some description",null));
		concept1.setDatatype(new ConceptDatatype(1));
		concept1.setConceptClass(new ConceptClass(1));
		Context.getConceptService().saveConcept(concept1);
		
		Concept concept2 = new Concept();
		concept2.addName(new ConceptName(name, new Locale("en", "GB")));
		concept2.addDescription(new ConceptDescription("some description",null));
		concept2.setDatatype(new ConceptDatatype(1));
		concept2.setConceptClass(new ConceptClass(1));
		Context.getConceptService().saveConcept(concept2);
		
		Concept concept3 = new Concept();
		concept3.addName(new ConceptName(name, new Locale("en")));
		concept3.addDescription(new ConceptDescription("some description",null));
		concept3.setDatatype(new ConceptDatatype(1));
		concept3.setConceptClass(new ConceptClass(1));
		Context.getConceptService().saveConcept(concept3);
		
		updateSearchIndex();
		
		//when
		List<Concept> concepts = Context.getConceptService().getConceptsByName(name, new Locale("en"), false);
		
		//then
		Assert.assertEquals(3, concepts.size());
		Assert.assertTrue(concepts.containsAll(Arrays.asList(concept1, concept2, concept3)));
	}