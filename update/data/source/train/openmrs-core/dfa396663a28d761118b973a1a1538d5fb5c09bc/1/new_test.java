@Test
	@Verifies(value = "should return Spring errors in ValidationException", method = "validate(Object)")
	public void validate_shouldThrowAPIExceptionIfErrorsOccurDuringValidation() throws Exception {
		Location loc = new Location();
		
		try {
			ValidateUtil.validate(loc);
		}
		catch (ValidationException validationException) {
			assertNotNull(validationException.getErrors());
			assertTrue(validationException.getErrors().hasErrors());
		}
		
	}