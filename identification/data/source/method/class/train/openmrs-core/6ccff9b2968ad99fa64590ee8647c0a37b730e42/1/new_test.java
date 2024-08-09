@Test
	@Verifies(value = "should fail validation if program name already in use", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfProgramNameAlreadyInUse() throws Exception {
		Program prog = new Program();
		prog.setName("MDR-TB PROGRAM");
		prog.setConcept(Context.getConceptService().getConcept(3));
		
		Errors errors = new BindException(prog, "prog");
		programValidator.validate(prog, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
	}