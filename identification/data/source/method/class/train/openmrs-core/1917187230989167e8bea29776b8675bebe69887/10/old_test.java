	@Test
	public void setFullySpecifiedName_shouldAddTheNameToTheListOfNamesIfItNotAmongThemBefore() {
		Concept concept = createConcept(1, Context.getLocale());
		int expectedNumberOfNames = concept.getNames().size() + 1;
		concept.setFullySpecifiedName(new ConceptName("some name", Context.getLocale()));
		Assert.assertEquals(expectedNumberOfNames, concept.getNames().size());
	}