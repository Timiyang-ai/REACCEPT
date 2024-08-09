@Test
	@Verifies(value = "should pass if all fields are correct", method = "validate(Object,Errors)")
	public void validate_shouldPassIfAllFieldsAreCorrect() throws Exception {
		OrderType orderType = new OrderType();
		orderType.setName("unique name");
		Errors errors = new BindException(orderType, "orderType");
		new OrderTypeValidator().validate(orderType, errors);
		
		Assert.assertFalse(errors.hasErrors());
	}