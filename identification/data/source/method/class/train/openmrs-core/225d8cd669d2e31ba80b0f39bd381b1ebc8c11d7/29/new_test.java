	@Test
	public void validate_shouldFailValidationIfUserIsNullOrEmptyOrWhitespace() {
		ConceptClass cc = new ConceptClass();
		cc.setName(null);
		cc.setDescription("some text");
		
		Errors errors = new BindException(cc, "cc");
		new ConceptClassValidator().validate(cc, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		cc.setName("");
		errors = new BindException(cc, "cc");
		new ConceptClassValidator().validate(cc, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		cc.setName(" ");
		errors = new BindException(cc, "cc");
		new ConceptClassValidator().validate(cc, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
	}