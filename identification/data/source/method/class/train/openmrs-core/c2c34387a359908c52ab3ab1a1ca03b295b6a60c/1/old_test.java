@Test
	@Verifies(value = "should fail validation if gender is blank", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfGenderIsBlank() throws Exception {
		Patient pa = new Patient(1);
		Errors errors = new BindException(pa, "patient");
		validator.validate(pa, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("gender"));
		
	}