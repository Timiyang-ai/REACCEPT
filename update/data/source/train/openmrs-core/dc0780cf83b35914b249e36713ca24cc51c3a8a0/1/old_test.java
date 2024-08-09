@Test
	@Verifies(value = "should pass validation if all required fields have proper values", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfAllRequiredFieldsHaveProperValues() throws Exception {
		Program prog = new Program();
		prog.setName("Hypochondriasis program");
		prog.setConcept(Context.getConceptService().getConcept(3));
		
		Errors errors = new BindException(prog, "prog");
		new ProgramValidator().validate(prog, errors);
		
		Assert.assertFalse(errors.hasErrors());
	}