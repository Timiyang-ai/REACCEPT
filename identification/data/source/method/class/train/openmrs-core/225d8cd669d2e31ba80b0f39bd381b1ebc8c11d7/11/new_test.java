	@Test
	public void validate_shouldFailValidationIfNameIsNull() {
		PersonAttributeType type = new PersonAttributeType();
		
		Errors errors = new BindException(type, "patObj");
		new PersonAttributeTypeValidator().validate(type, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("name"));
	}