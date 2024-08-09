	@Test
	public void validate_shouldFailValidationIfNameIsNullOrEmptyOrWhitespace() {
		ConceptSource conceptSource = new ConceptSource();
		conceptSource.setName(null);
		conceptSource.setDescription("Some description");
		
		Errors errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		conceptSource.setName("");
		errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		conceptSource.setName("   ");
		errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
	}