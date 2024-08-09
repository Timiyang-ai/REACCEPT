@Test
	@Verifies(value = "getBestShortName should not return voided conceptName, should return non-voided concept in other locale even if not short", method = "getBestShortName(Locale)")
	public void getBestShortName_shouldReturnNonVoidedConceptName() throws Exception {
		Locale localeToSearch = new Locale("en");
		Locale nonMatchingNameLocale = new Locale("fr");
		Concept concept = new Concept();
		
		ConceptName conceptName = new ConceptName("some name", localeToSearch);
		conceptName.setVoided(true);
		concept.setShortName(localeToSearch, conceptName);
		
		ConceptName conceptNameOther = new ConceptName("some other name", nonMatchingNameLocale);
		concept.addName(conceptNameOther);
		
		ConceptName cn = concept.getBestShortName(localeToSearch);
		Assert.assertEquals(cn.getLocale(), nonMatchingNameLocale);
		Assert.assertEquals(cn.getName(), "some other name");
	}