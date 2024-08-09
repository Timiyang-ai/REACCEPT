	@Test
	public void validate_shouldPassValidationIfFieldLengthsAreCorrect() {
		VisitType visitType = new VisitType();
		visitType.setName("name");
		visitType.setDescription("some text");
		visitType.setRetireReason("some text");
		
		Errors errors = new BindException(visitType, "visitType");
		new VisitTypeValidator().validate(visitType, errors);
		
		Assert.assertFalse(errors.hasErrors());
	}