@Test
	@Verifies(value = "should fail validation if unlocalized name is null or empty or whitespace", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfUnlocalizedNameIsNullOrEmptyOrWhitespace()
			throws Exception {
		OrderType type = new OrderType();
		type.setName(null);
		type.setDescription(":(");
		
		Errors errors = new BindException(type, "type");
		new OrderTypeValidator().validate(type, errors);
		Assert.assertTrue(errors.hasFieldErrors("localizedName.unlocalizedValue"));
		
		type.setName("");
		errors = new BindException(type, "type");
		new OrderTypeValidator().validate(type, errors);
		Assert.assertTrue(errors.hasFieldErrors("localizedName.unlocalizedValue"));
		
		type.setName(" ");
		errors = new BindException(type, "type");
		new OrderTypeValidator().validate(type, errors);
		Assert.assertTrue(errors.hasFieldErrors("localizedName.unlocalizedValue"));
	}