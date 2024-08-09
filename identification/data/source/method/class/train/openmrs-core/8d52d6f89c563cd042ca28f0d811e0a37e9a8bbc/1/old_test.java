	@Test(expected = IllegalArgumentException.class)
	public void validate_shouldFailIfTheOrderTypeObjectIsNull() {
		Errors errors = new BindException(new OrderType(), "orderType");
		new OrderTypeValidator().validate(null, errors);
	}