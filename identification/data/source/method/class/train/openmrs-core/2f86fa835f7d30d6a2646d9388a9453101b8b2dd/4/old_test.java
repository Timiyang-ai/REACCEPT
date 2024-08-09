@Test
	@Verifies(value = "should pass if the concept has a synonym that is also a short name", method = "validate(Object,Errors)")
	public void validate_shouldPassIfTheConceptHasASynonymThatIsAlsoAShortName() throws Exception {
		Concept concept = new Concept();
		concept.addName(new ConceptName("CD4", Context.getLocale()));
		// Add the short name. Because the short name is not counted as a Synonym. 
		// ConceptValidator will not record any errors.
		ConceptName name = new ConceptName("CD4", Context.getLocale());
		name.setConceptNameType(ConceptNameType.SHORT);
		concept.addName(name);
		Errors errors = new BindException(concept, "concept");
		new ConceptValidator().validate(concept, errors);
		Assert.assertFalse(errors.hasErrors());
	}