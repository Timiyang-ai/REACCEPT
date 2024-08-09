@Test
	public void discontinueOrder_shouldSetCorrectAttributesOnTheDiscontinueAndDiscontinuedOrders() throws Exception {
		executeDataSet("org/openmrs/api/include/OrderServiceTest-discontinueReason.xml");
		
		Order order = orderService.getOrderByOrderNumber("111");
		Encounter encounter = encounterService.getEncounter(3);
		Provider orderer = providerService.getProvider(1);
		Date discontinueDate = new Date();
		Concept concept = Context.getConceptService().getConcept(1);
		
		Order discontinueOrder = orderService.discontinueOrder(order, concept, discontinueDate, orderer, encounter);
		
		// Reflecting changes from setStartDate to setDateActivated in the assertion
		Assert.assertEquals("Check for correct dateStopped on the discontinued order", order.getDateStopped(), discontinueDate);
		Assert.assertNotNull("Ensure the discontinue order is not null", discontinueOrder);
		Assert.assertNotNull("Ensure the discontinue order has an ID", discontinueOrder.getId());
		Assert.assertEquals("Ensure dateActivated is set correctly on the discontinue order", discontinueOrder.getDateActivated(), discontinueOrder.getAutoExpireDate());
		Assert.assertEquals("Check for correct action 'DISCONTINUE' on the discontinue order", discontinueOrder.getAction(), Action.DISCONTINUE);
		Assert.assertEquals("Ensure order reason is set correctly on the discontinue order", discontinueOrder.getOrderReason(), concept);
		Assert.assertEquals("Verify that previousOrder is set correctly on the discontinue order", discontinueOrder.getPreviousOrder(), order);
	}