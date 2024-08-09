@Test
	@Verifies(value = "should pass validation if all fields are correct", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfAllFieldsAreCorreect() throws Exception {
		PersonAttributeType type = new PersonAttributeType();
		type.setName("Zodiac");
		
		Errors errors = new BindException(type, "patObj");
		new PersonAttributeTypeValidator().validate(type, errors);
		
		Assert.assertFalse(errors.hasErrors());
	}