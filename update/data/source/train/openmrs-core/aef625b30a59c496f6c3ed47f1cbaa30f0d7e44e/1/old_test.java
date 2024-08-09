@Test(expected = DuplicateConceptNameException.class)
	@Verifies(value = "should fail if any names in the same locale for this concept are similar", method = "validate(Object,Errors)")
	public void validate_shouldFailIfAnyNamesInTheSameLocaleForThisConceptAreSimilar() throws Exception {
		Concept concept = new Concept();
		concept.addName(new ConceptName("same name", Context.getLocale()));
		concept.addName(new ConceptName("same name", Context.getLocale()));
		concept.addDescription(new ConceptDescription("some description",null));
		Errors errors = new BindException(concept, "concept");
		new ConceptValidator().validate(concept, errors);
	}