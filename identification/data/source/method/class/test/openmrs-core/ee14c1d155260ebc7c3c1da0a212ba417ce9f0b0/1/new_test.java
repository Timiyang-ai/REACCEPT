@Test
	public void validate_shouldFailValidationIfDoseUnitsIsNullForSimpleDosingInstructionsDosingType() throws Exception {
		DrugOrder order = new DrugOrder();
		order.setDosingType(SimpleDosingInstructions.class);
		order.setDoseUnits(null);
		Errors errors = new BindException(order, "order");
		new DrugOrderValidator().validate(order, errors);
		Assert.assertTrue(errors.hasFieldErrors("doseUnits"));
	}