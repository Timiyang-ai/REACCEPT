@Test
	@Verifies(value = "should fail validation if drug is null", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfDrugIsNull() throws Exception {
		DrugOrder order = new DrugOrder();
		
		Errors errors = new BindException(order, "order");
		new DrugOrderValidator().validate(order, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("drug"));
	}