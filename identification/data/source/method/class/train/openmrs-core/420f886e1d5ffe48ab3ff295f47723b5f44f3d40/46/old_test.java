	@Test
	public void validate_shouldFailValidationIfNameIsNullOrEmptyOrWhitespace() {
		PatientIdentifierType type = new PatientIdentifierType();
		type.setName(null);
		type.setDescription("some text");
		
		Errors errors = new BindException(type, "type");
		new PatientIdentifierTypeValidator().validate(type, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		type.setName("");
		errors = new BindException(type, "type");
		new PatientIdentifierTypeValidator().validate(type, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		type.setName(" ");
		errors = new BindException(type, "type");
		new PatientIdentifierTypeValidator().validate(type, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
	}