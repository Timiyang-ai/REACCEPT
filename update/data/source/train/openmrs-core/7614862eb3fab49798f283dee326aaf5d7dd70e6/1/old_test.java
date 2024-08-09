@Test
	@Verifies(value = "should return exact name locale match given exact equals true", method = "getName(Locale,null)")
	public void getName_shouldReturnExactNameLocaleMatchGivenExactEqualsTrue() throws Exception {
		Locale definedNameLocale = new Locale("en", "US");
		Locale localeToSearch = new Locale("en", "US");
		
		Concept concept = new Concept();
		concept.addName(new ConceptName("some name", definedNameLocale));
		Assert.assertNotNull(concept.getName(localeToSearch, true));
		Assert.assertEquals("some name", concept.getName(localeToSearch, true).getName());
	}