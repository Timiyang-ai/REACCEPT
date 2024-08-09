@Test
	@Verifies(value = "should weigh a word for a synonym higher than that of a short name", method = "weighConceptWord(ConceptWord)")
	public void weighConceptWord_shouldWeighAWordForASynonymHigherThanThatOfAShortName() throws Exception {
		Concept c = new Concept();
		
		ConceptName synonymName = new ConceptName("my depot", Locale.ENGLISH);
		ConceptWord synonymWord = new ConceptWord("my", c, synonymName, Locale.ENGLISH);
		
		ConceptName shortName = new ConceptName("my depot", Locale.ENGLISH);
		ConceptWord shortWord = new ConceptWord("my", c, shortName, Locale.ENGLISH);
		shortName.setConceptNameType(ConceptNameType.SHORT);
		
		Assert.assertTrue("A word for a synonym should weigh higher than that of a short name", dao
		        .weighConceptWord(synonymWord) > dao.weighConceptWord(shortWord));
	}