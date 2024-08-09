	@Test
	public void validate_shouldFailValidationIfNameIsNull() {
		Form form = new Form();
		form.setVersion("1.0");
		
		Errors errors = new BindException(form, "form");
		new FormValidator().validate(form, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("name"));
		Assert.assertFalse(errors.hasFieldErrors("version"));
	}