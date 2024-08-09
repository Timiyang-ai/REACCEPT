	@Test
	public void validate_shouldFailValidationIfNameIsNullOrEmptyOrWhitespace() {
		FieldType type = new FieldType();
		type.setName(null);
		type.setDescription("Humba humba humba ...");
		
		Errors errors = new BindException(type, "type");
		new FieldTypeValidator().validate(type, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		type.setName("");
		errors = new BindException(type, "type");
		new FieldTypeValidator().validate(type, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		type.setName(" ");
		errors = new BindException(type, "type");
		new FieldTypeValidator().validate(type, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
	}