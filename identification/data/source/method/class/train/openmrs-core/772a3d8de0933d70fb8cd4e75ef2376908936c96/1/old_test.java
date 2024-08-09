@Test
	@Verifies(value = "should pass validation if field lengths are correct", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfFieldLengthsAreCorrect() throws Exception {
		Concept concept = new Concept();
		concept.addName(new ConceptName("CD4", Context.getLocale()));
		concept.setVersion("version");
		concept.setRetireReason("retireReason");
		
		Errors errors = new BindException(concept, "concept");
		new ConceptValidator().validate(concept, errors);
		Assert.assertFalse(errors.hasErrors());
	}