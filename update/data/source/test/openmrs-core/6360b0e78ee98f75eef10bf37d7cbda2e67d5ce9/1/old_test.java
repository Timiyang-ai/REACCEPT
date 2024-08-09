@Test
	@Verifies(value = "should fail validation if discontinued is null", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfDiscontinuedIsNull() throws Exception {
		Order order = new Order();
		order.setDiscontinued(null);
		order.setConcept(Context.getConceptService().getConcept(88));
		order.setPatient(Context.getPatientService().getPatient(2));
		order.setOrderType(Context.getOrderService().getOrderType(1));
		
		Errors errors = new BindException(order, "order");
		new OrderValidator().validate(order, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("discontinued"));
		Assert.assertFalse(errors.hasFieldErrors("concept"));
		Assert.assertFalse(errors.hasFieldErrors("patient"));
		Assert.assertFalse(errors.hasFieldErrors("orderType"));
	}