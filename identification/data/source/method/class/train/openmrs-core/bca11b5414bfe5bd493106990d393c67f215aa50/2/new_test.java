	@Test
	public void validate_shouldPassValidationIfFieldLengthsAreCorrect() {
		LocationTag locationTag = new LocationTag();
		
		locationTag.setName("name");
		locationTag.setDescription("description");
		locationTag.setRetireReason("retireReason");
		
		Errors errors = new BindException(locationTag, "locationTag");
		new LocationTagValidator().validate(locationTag, errors);
		
		Assert.assertFalse(errors.hasErrors());
	}