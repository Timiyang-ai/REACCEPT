@Test
	@Verifies(value = "should pass if fully specified name is the same as short name", method = "validate(Object,Errors)")
	public void validate_shouldPassIfFullySpecifiedNameIsTheSameAsShortName() throws Exception {
		Concept concept = new Concept();
		
		ConceptName conceptFullySpecifiedName = new ConceptName("YES", new Locale("pl"));
		conceptFullySpecifiedName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
		
		ConceptName conceptShortName = new ConceptName("yes", new Locale("pl"));
		conceptShortName.setConceptNameType(ConceptNameType.SHORT);
		
		concept.addName(conceptFullySpecifiedName);
		concept.addName(conceptShortName);
		concept.addDescription(new ConceptDescription("some description",null));
		
		Errors errors = new BindException(concept, "concept");
		new ConceptValidator().validate(concept, errors);
		Assert.assertEquals(false, errors.hasErrors());
	}