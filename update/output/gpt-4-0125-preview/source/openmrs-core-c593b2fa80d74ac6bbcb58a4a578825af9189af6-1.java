@Test
	public void saveOrder_shouldFailIfAnActiveDrugOrderForTheSameOrderableAndOverlappingScheduleExists() throws Exception {
		final Patient patient = patientService.getPatient(2);
		final Concept cd4Count = conceptService.getConcept(5497);
		//sanity check that we have an active drug order for the same orderable
		DrugOrder duplicateOrder = (DrugOrder) orderService.getOrder(7);
		assertTrue(duplicateOrder.isActive());
		assertEquals(cd4Count, duplicateOrder.getConcept());
		
		DrugOrder order = new DrugOrder();
		order.setPatient(patient);
		order.setCareSetting(orderService.getCareSetting(2));
		order.setConcept(cd4Count);
		order.setEncounter(encounterService.getEncounter(6));
		order.setOrderer(providerService.getProvider(1));
		order.setCareSetting(duplicateOrder.getCareSetting());
		// Setting dates to ensure overlapping schedule
		order.setDateActivated(duplicateOrder.getDateActivated());
		order.setAutoExpireDate(duplicateOrder.getAutoExpireDate());
		
		expectedException.expect(APIException.class);
		expectedException.expectMessage("Order.cannot.have.more.than.one");
		orderService.saveOrder(order, null);
	}