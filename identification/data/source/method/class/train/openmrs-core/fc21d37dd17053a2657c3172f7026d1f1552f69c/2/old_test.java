	@Test
	public void validate_shouldPassValidationIfFieldLengthsAreCorrect() {
		VisitAttributeType visitAttributeType = new VisitAttributeType();
		visitAttributeType.setName("name");
		visitAttributeType.setMinOccurs(1);
		visitAttributeType.setDatatypeConfig("[a-z]+");
		visitAttributeType.setDatatypeClassname(RegexValidatedTextDatatype.class.getName());
		visitAttributeType.setDescription("some text");
		visitAttributeType.setRetireReason("some text");
		
		Errors errors = new BindException(visitAttributeType, "visitAttributeType");
		new VisitAttributeTypeValidator().validate(visitAttributeType, errors);
		
		Assert.assertFalse(errors.hasErrors());
	}