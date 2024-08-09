@Test(expected = APIException.class)
	@Verifies(value = "should fail if the preferred name is an empty string", method = "validate(Concept)")
	public void validate_shouldFailIfThePreferredNameIsAnEmptyString() throws Exception {
		
		Concept concept = new Concept();
		concept.setPreferredName(Context.getLocale(), new ConceptName("", Context.getLocale()));
		Errors errors = new BindException(concept, "concept");
		new ConceptValidator().validate(concept, errors);
		Assert.assertTrue(errors.hasErrors());
		
	}