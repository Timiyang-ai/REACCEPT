	@Test
	public void validate_shouldFailValidationIfNameIsNullOrEmptyOrWhitespace() {
		ConceptDatatype cd = new ConceptDatatype();
		cd.setName(null);
		cd.setDescription("some text");
		
		Errors errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		cd.setName("");
		errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		cd.setName(" ");
		errors = new BindException(cd, "cd");
		new ConceptDatatypeValidator().validate(cd, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
	}