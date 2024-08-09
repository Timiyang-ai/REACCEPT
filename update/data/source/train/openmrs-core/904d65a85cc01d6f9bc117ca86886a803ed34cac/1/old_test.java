@Test
	@Verifies(value = "should pass validation if description is null or empty or whitespace", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfDescriptionIsNullOrEmptyOrWhitespace() throws Exception {
		ConceptSource conceptSource = new ConceptSource();
		conceptSource.setName("New name");
		conceptSource.setDescription(null);
		
		Errors errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertFalse(errors.hasFieldErrors("description"));
		
		conceptSource.setDescription("");
		errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertFalse(errors.hasFieldErrors("description"));
		
		conceptSource.setDescription("   ");
		errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertFalse(errors.hasFieldErrors("description"));
	}