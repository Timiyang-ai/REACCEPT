	@Test
	public void validate_shouldFailValidationIfNameIsNullOrEmptyOrWhitespace() {
		Program prog = new Program();
		prog.setName(null);
		prog.setConcept(Context.getConceptService().getConcept(3));
		
		Errors errors = new BindException(prog, "prog");
		programValidator.validate(prog, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		prog.setName("");
		errors = new BindException(prog, "prog");
		programValidator.validate(prog, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		prog.setName(" ");
		errors = new BindException(prog, "prog");
		programValidator.validate(prog, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
	}