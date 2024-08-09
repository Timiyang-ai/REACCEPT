@Test
	@Verifies(value = "should fail validation if dosingType is null", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfDosingTypeIsNull() throws Exception {
		DrugOrder order = new DrugOrder();
		order.setDosingType(null);
		order.setDrug(Context.getConceptService().getDrug(3));
		
		Errors errors = new BindException(order, "order");
		new DrugOrderValidator().validate(order, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("dosingType"));
	}