	@Test
	public void validate_shouldFailValidationIfPersonNameObjectIsNull() {
		
		validator.validate(null, errors);
		
		assertThat(errors, hasGlobalErrors("error.name"));
	}