@Test
	@Verifies(value = "should return exact name locale match given exact equals true", method = "getName(Locale,null)")
	public void getName_shouldReturnExactNameLocaleMatchGivenExactEqualsTrue() throws Exception {
		Locale definedNameLocale = new Locale("en", "US");
		Locale localeToSearch = new Locale("en", "US");
		
		Concept concept = new Concept();
		ConceptName fullySpecifiedName = new ConceptName("some name", definedNameLocale);
		fullySpecifiedName.setConceptNameId(1);
		fullySpecifiedName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
		fullySpecifiedName.setLocalePreferred(false);
		concept.addName(fullySpecifiedName);
		Assert.assertNotNull(concept.getName(localeToSearch, true));
		Assert.assertEquals("some name", concept.getName(localeToSearch, true).getName());
	}