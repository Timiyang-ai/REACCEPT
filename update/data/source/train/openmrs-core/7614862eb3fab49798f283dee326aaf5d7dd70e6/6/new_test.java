@Test
	@SkipBaseSetup
	@Verifies(value = "should save a ConceptNumeric as a concept", method = "saveConcept(Concept)")
	public void saveConcept_shouldSaveAConceptNumericAsAConcept() throws Exception {
		initializeInMemoryDatabase();
		executeDataSet(INITIAL_CONCEPTS_XML);
		authenticate();
		//This will automatically add the given locale to the list of allowed locales
		Context.setLocale(Locale.US);
		// this tests saving a previously conceptnumeric as just a concept
		Concept c2 = new Concept(2);
		ConceptName cn = new ConceptName("not a numeric anymore", Locale.US);
		c2.addName(cn);
		
		c2.setDatatype(new ConceptDatatype(3));
		conceptService.saveConcept(c2);
		
		Concept secondConcept = conceptService.getConcept(2);
		// this will probably still be a ConceptNumeric object.  what to do about that?
		// revisit this problem when discriminators are in place
		//assertFalse(secondConcept instanceof ConceptNumeric);
		// this shouldn't think its a conceptnumeric object though
		assertFalse(secondConcept.isNumeric());
		assertEquals("not a numeric anymore", secondConcept.getName(Locale.US).getName());
		
	}