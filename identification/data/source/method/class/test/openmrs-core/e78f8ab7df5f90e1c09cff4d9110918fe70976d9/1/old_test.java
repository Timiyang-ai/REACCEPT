	@Test(expected = ValidationException.class)
	public void validate_shouldThrowValidationExceptionIfErrorsOccurDuringValidation() {
		Location loc = new Location();
		ValidateUtil.validate(loc);
	}