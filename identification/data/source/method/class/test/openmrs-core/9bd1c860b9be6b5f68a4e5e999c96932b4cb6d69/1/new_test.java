	@Test
	public void addName_shouldMarkTheFirstNameAddedAsFullySpecified() {
		Concept concept = new Concept();
		concept.addName(new ConceptName("some name", Context.getLocale()));
		Assert.assertEquals("some name", concept.getFullySpecifiedName(Context.getLocale()).getName());
	}