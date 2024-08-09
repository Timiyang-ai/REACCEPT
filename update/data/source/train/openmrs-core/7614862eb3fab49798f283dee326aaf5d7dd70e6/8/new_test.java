@Test
	@Verifies(value = "should fail if any name is an empty string", method = "validate(Object,Errors)")
	public void validate_shouldFailIfAnyNameIsAnEmptyString() throws Exception {
		Concept concept = new Concept();
		concept.addName(new ConceptName("name", Context.getLocale()));
		concept.addName(new ConceptName("", Context.getLocale()));
		Errors errors = new BindException(concept, "concept");
		new ConceptValidator().validate(concept, errors);
		Assert.assertEquals(true, errors.hasErrors());
	}