@Test
	public void validate_shouldFailValidationIfDoseUnitsIsNotADoseUnitConcept() throws Exception {
		Concept concept = Context.getConceptService().getConcept(3);
		assertThat(concept, not(isIn(Context.getOrderService().getDrugDosingUnits())));
		
		DrugOrder order = new DrugOrder();
		order.setDosingType(DrugOrder.DosingType.FREE_TEXT);
		order.setDuration(5.0);
		order.setDurationUnits(concept);
		order.setDose(1.0);
		order.setDoseUnits(concept);
		order.setQuantity(30.0);
		order.setQuantityUnits(concept);
		
		Errors errors = new BindException(order, "order");
		new DrugOrderValidator().validate(order, errors);
		Assert.assertTrue(errors.hasFieldErrors("doseUnits"));
	}