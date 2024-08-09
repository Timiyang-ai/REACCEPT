	@Test
	public void setShortName_shouldAddTheNameToTheListOfNamesIfItNotAmongThemBefore() {
		Concept concept = createConcept(1, Context.getLocale());
		int expectedNumberOfNames = concept.getNames().size() + 1;
		concept.setShortName(new ConceptName("some name", Context.getLocale()));
		Assert.assertEquals(expectedNumberOfNames, concept.getNames().size());
	}