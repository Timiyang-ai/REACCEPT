	@Test
	public void validate_shouldFailValidationIfOrderIsNull() {
		Errors errors = new BindException(new Order(), "order");
		new OrderValidator().validate(null, errors);
		
		Assert.assertTrue(errors.hasErrors());
		Assert.assertEquals("error.general", errors.getAllErrors().get(0).getCode());
	}