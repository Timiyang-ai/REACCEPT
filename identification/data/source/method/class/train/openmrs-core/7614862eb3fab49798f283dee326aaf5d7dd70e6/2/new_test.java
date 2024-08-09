@Test
	@Verifies(value = "return null if no names are found in locale given exact equals true", method = "getName(Locale,null)")
	public void getName_shouldReturnNullIfNoNamesAreFoundInLocaleGivenExactEqualsTrue() throws Exception {
		Locale nonMatchingNameLocale = new Locale("en", "US");
		Locale localeToSearch = new Locale("en");
		
		Concept concept = new Concept();
		concept.addName(new ConceptName("some name", nonMatchingNameLocale));
		Assert.assertNull(concept.getName(localeToSearch, true));
	}