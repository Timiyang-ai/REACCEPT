@Test(expected = APIException.class)
	@Verifies(value = "should throw APIException if errors occur during validation", method = "validate(Object)")
	public void validate_shouldThrowAPIExceptionIfErrorsOccurDuringValidation() throws Exception {
		Location loc = new Location();
		ValidateUtil.validate(loc);
	}