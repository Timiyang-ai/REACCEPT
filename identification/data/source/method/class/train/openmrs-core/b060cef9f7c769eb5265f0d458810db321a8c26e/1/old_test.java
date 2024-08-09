	@Test
	public void validate_shouldFailValidationIfTheSpecimenSourceIsInvalid() {
		ConceptService conceptService = Context.getConceptService();
		Concept specimenSource = conceptService.getConcept(3);
		OrderService orderService = Context.getOrderService();
		assertThat(specimenSource, not(isIn(orderService.getDrugRoutes())));
		TestOrder order = new TestOrder();
		Patient patient = new Patient(8);
		order.setPatient(patient);
		order.setOrderType(orderService.getOrderTypeByName("Test order"));
		order.setConcept(conceptService.getConcept(5497));
		order.setOrderer(new Provider());
		order.setCareSetting(new CareSetting());
		Encounter encounter = new Encounter();
		encounter.setPatient(patient);
		order.setEncounter(encounter);
		order.setDateActivated(new Date());
		order.setSpecimenSource(specimenSource);
		
		Errors errors = new BindException(order, "order");
		new TestOrderValidator().validate(order, errors);
		Assert.assertTrue(errors.hasFieldErrors("specimenSource"));
		Assert.assertEquals("TestOrder.error.specimenSourceNotAmongAllowedConcepts", errors.getFieldError("specimenSource")
		        .getCode());
	}