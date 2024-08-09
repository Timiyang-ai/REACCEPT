@Test
	public void validate_shouldPassValidationIfAllRequiredFieldsAreSet() throws Exception {
		RelationshipType type = new RelationshipType();
		type.setaIsToB("A is To B");
		type.setbIsToA("B is To A");
		
		Errors errors = new BindException(type, "type");
		new RelationshipTypeValidator().validate(type, errors);
		Assert.assertFalse(errors.hasErrors());
	}