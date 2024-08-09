@Test
	@Verifies(value = "should fail validation if complex is null", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfComplexIsNull() throws Exception {
		DrugOrder order = new DrugOrder();
		order.setComplex(null);
		order.setDrug(Context.getConceptService().getDrug(3));
		
		Errors errors = new BindException(order, "order");
		new DrugOrderValidator().validate(order, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("complex"));
	}