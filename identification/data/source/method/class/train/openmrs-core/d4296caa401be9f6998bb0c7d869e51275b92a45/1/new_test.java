@Test
	public void saveOrder_shouldSetOrderTypeIfNullButMappedToTheConceptClass() throws Exception {
		Order order = new Order();
		order.setPatient(patientService.getPatient(7));
		order.setConcept(conceptService.getConcept(5497));
		order.setOrderer(providerService.getProvider(1));
		order.setCareSetting(orderService.getCareSetting(1));
		order.setEncounter(encounterService.getEncounter(3));
		order.setStartDate(new Date());
		order = orderService.saveOrder(order, null);
		assertEquals(2, order.getOrderType().getOrderTypeId().intValue());
	}