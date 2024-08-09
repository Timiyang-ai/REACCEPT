@Test
	public void saveOrder_shouldSetOrderTypeIfNullButMappedToTheConceptClass() throws Exception {
		executeDataSet(OTHER_ENCOUNTERS_XML);
		Order order = new Order();
		order.setPatient(patientService.getPatient(2));
		order.setConcept(conceptService.getConcept(5497));
		order.setOrderer(providerService.getProvider(1));
		order.setCareSetting(orderService.getCareSetting(1));
		order.setEncounter(encounterService.getEncounter(6));
		order.setStartDate(new Date());
		order = orderService.saveOrder(order, null);
		assertEquals(2, order.getOrderType().getOrderTypeId().intValue());
	}