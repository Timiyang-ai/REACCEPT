@Test
	public void validate_shouldFailValidationIfStartDateIsBeforeEncountersEncounterDatetime() throws Exception {
		Date encounterDate = new Date();
		Date orderDate = DateUtils.addDays(encounterDate, -1);
		Encounter encounter = Context.getEncounterService().getEncounter(3);
		encounter.setEncounterDatetime(encounterDate);
		Order order = new Order();
		order.setStartDate(orderDate);
		order.setConcept(Context.getConceptService().getConcept(88));
		order.setPatient(Context.getPatientService().getPatient(2));
		order.setEncounter(encounter);
		order.setOrderer(Context.getProviderService().getProvider(1));
		Errors errors = new BindException(order, "order");
		new OrderValidator().validate(order, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("startDate"));
	}