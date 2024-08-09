	@Test
	public void hasName_shouldReturnFalseIfNameIsNull() {
		Concept concept = new Concept();
		concept.addName(new ConceptName("Test Concept", new Locale("en")));
		Locale localeToSearch = new Locale("en", "UK");
		Assert.assertFalse(concept.hasName(null, localeToSearch));
	}