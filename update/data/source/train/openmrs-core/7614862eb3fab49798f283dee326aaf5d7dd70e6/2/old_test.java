@Test
	@Verifies(value = "should return null if no locale match and exact equals true", method = "getName(Locale,null)")
	public void getName_shouldReturnNullIfNoLocaleMatchAndExactEqualsTrue() throws Exception {
		Locale nonMatchingNameLocale = new Locale("en", "US");
		Locale localeToSearch = new Locale("en");
		
		Concept concept = new Concept();
		concept.addName(new ConceptName("some name", nonMatchingNameLocale));
		Assert.assertNull(concept.getName(localeToSearch, true));
	}