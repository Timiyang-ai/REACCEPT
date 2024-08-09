@Test
	public void validate_shouldFailIfDurationUnitsHasNoMappingToISO8601Source() throws Exception {
		Patient patient = Context.getPatientService().getPatient(7);
		CareSetting careSetting = Context.getOrderService().getCareSetting(2);
		OrderType orderType = Context.getOrderService().getOrderTypeByName("Drug order");
		
		//place drug order
		DrugOrder order = new DrugOrder();
		Encounter encounter = Context.getEncounterService().getEncounter(3);
		order.setEncounter(encounter);
		ConceptService cs = Context.getConceptService();
		order.setConcept(cs.getConcept(5497));
		order.setPatient(patient);
		order.setCareSetting(careSetting);
		order.setOrderer(Context.getProviderService().getProvider(1));
		order.setDateActivated(encounter.getEncounterDatetime());
		order.setOrderType(orderType);
		order.setDosingType(FreeTextDosingInstructions.class);
		order.setInstructions("None");
		order.setDosingInstructions("Test Instruction");
		order.setDuration(20);
		order.setDurationUnits(cs.getConcept(28));
		Errors errors = new BindException(order, "order");
		new DrugOrderValidator().validate(order, errors);
		assertEquals("DrugOrder.error.durationUnitsNotMappedToISO8601DurationCode", errors.getFieldError("durationUnits")
		        .getCode());
	}