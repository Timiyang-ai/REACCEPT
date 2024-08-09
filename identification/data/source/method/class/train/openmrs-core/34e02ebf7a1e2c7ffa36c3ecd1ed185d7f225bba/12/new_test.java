	@Test
	public void validate_shouldFailValidationIfAsNeededIsNull() {
		DrugOrder order = new DrugOrder();
		order.setAsNeeded(null);
		order.setDrug(Context.getConceptService().getDrug(3));
		
		Errors errors = new BindException(order, "order");
		new DrugOrderValidator().validate(order, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("asNeeded"));
	}