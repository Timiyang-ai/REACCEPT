	@Test
	public void validate_shouldFailIfOrderFrequencyIsNull() {
		Errors errors = new BindException(new OrderFrequency(), "orderFrequency");
		new OrderFrequencyValidator().validate(null, errors);
		
		Assert.assertTrue(errors.hasErrors());
	}