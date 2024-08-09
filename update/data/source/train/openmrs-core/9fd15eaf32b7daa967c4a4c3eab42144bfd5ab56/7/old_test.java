@Test
	public void saveOrder_shouldSetOrderTypeOfTestOrderToTestOrderIfNotSetAndConceptNotMapped() throws Exception {
		TestOrder testOrder = new TestOrder();
		testOrder.setPatient(patientService.getPatient(7));
		Concept unmappedConcept = conceptService.getConcept(113);
		
		Assert.assertNull(orderService.getOrderTypeByConcept(unmappedConcept));
		testOrder.setConcept(unmappedConcept);
		testOrder.setOrderer(providerService.getProvider(1));
		testOrder.setCareSetting(orderService.getCareSetting(1));
		Encounter encounter = encounterService.getEncounter(3);
		testOrder.setEncounter(encounter);
		testOrder.setStartDate(encounter.getEncounterDatetime());
		testOrder.setClinicalHistory("Patient had a negative reaction to the test in the past");
		testOrder.setFrequency(orderService.getOrderFrequency(3));
		testOrder.setSpecimenSource(conceptService.getConcept(22));
		testOrder.setNumberOfRepeats(3);
		
		orderService.saveOrder(testOrder, null);
		Assert.assertNotNull(testOrder.getOrderType());
		Assert.assertEquals(orderService.getOrderTypeByUuid(OrderType.TEST_ORDER_TYPE_UUID), testOrder.getOrderType());
	}