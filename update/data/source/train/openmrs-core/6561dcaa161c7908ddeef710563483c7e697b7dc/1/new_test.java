@Test
	public void saveOrder_shouldFailIfOrderTypeIsNullAndNotMappedToTheConceptClass() throws Exception {
		Order order = new Order();
		order.setPatient(patientService.getPatient(2));
		order.setConcept(conceptService.getConcept(3));
		order.setOrderer(providerService.getProvider(1));
		order.setCareSetting(orderService.getCareSetting(1));
		order.setEncounter(encounterService.getEncounter(6));
		order.setStartDate(new Date());
		expectedException.expect(APIException.class);
		expectedException.expectMessage("Cannot determine the order type of the order");
		orderService.saveOrder(order, null);
	}