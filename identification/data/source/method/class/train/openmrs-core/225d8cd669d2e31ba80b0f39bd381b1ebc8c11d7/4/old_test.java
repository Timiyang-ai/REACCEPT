	@Test
	public void validate_shouldFailValidationIfNameIsNullOrEmptyOrWhitespace() {
		EncounterRole role = new EncounterRole();
		role.setName(null);
		role.setDescription(":(");
		
		Errors errors = new BindException(role, "type");
		new RequireNameValidator().validate(role, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		role.setName("");
		errors = new BindException(role, "type");
		new RequireNameValidator().validate(role, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		role.setName(" ");
		errors = new BindException(role, "type");
		new RequireNameValidator().validate(role, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
	}