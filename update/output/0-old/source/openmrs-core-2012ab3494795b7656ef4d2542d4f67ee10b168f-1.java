@Test
	public void saveConcept_shouldLeavePreferredNamePreferredIfSet() throws Exception {
		Locale loc = new Locale("fr", "CA");
		ConceptName fullySpecifiedName = new ConceptName("fully specified", loc);
		fullySpecifiedName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED); //be explicit for test case
		ConceptName shortName = new ConceptName("short name", loc);
		shortName.setConceptNameType(ConceptNameType.SHORT); //be explicit for test case
		ConceptName synonym = new ConceptName("synonym", loc);
		synonym.setConceptNameType(null); //synonyms are id'd by a null type
		ConceptName indexTerm = new ConceptName("indexTerm", loc);
		indexTerm.setConceptNameType(ConceptNameType.INDEX_TERM); //synonyms are id'd by a null type
		
		//saveConcept never picks an index term for default, so we'll use it for the test
		indexTerm.setLocalePreferred(true);
		
		Concept c = new Concept();
		c.addName(fullySpecifiedName);
		c.addName(synonym);
		c.addName(indexTerm);
		c.addName(shortName);
		
		//ignore it so we can test the set default preferred name  functionality
		try {
			Context.getConceptService().saveConcept(c);
		}
		catch (org.openmrs.api.APIException e) {
			//ignore it
		}
		assertNotNull("there's a preferred name", c.getPreferredName(loc));
		assertTrue("name was explicitly marked preferred", c.getPreferredName(loc).isPreferred());
		assertEquals("name matches", c.getPreferredName(loc).getName(), indexTerm.getName());
	}