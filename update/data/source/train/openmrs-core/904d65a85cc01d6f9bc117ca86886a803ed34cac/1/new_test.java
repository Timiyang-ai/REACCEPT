@Test
	public void validate_shouldFailValidationIfDescriptionIsNullOrEmptyOrWhitespace() throws Exception {

		ConceptSource conceptSource = new ConceptSource();
		conceptSource.setName("New name");

		conceptSource.setDescription(null);
		Errors errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertTrue(errors.hasFieldErrors("description"));
		
		conceptSource.setDescription("");
		errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertTrue(errors.hasFieldErrors("description"));
		
		conceptSource.setDescription("   ");
		errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertTrue(errors.hasFieldErrors("description"));
	}