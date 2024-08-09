@Test
	public void discontinueOrder_shouldSetCorrectAttributesOnTheDiscontinueAndDiscontinuedOrders() throws Exception {
		executeDataSet("org/openmrs/api/include/OrderServiceTest-discontinueReason.xml");
		
		Order order = orderService.getOrderByOrderNumber("111");
		Encounter encounter = encounterService.getEncounter(3);
		Provider orderer = providerService.getProvider(1);
		Date discontinueDate = new Date();
		Concept concept = Context.getConceptService().getConcept(1);
		
		Order discontinueOrder = orderService.discontinueOrder(order, concept, discontinueDate, orderer, encounter);
		
		Assert.assertEquals(order.getDateStopped(), discontinueDate);
		Assert.assertNotNull(discontinueOrder);
		Assert.assertNotNull(discontinueOrder.getId());
		Assert.assertEquals(discontinueOrder.getStartDate(), discontinueOrder.getAutoExpireDate());
		Assert.assertEquals(discontinueOrder.getAction(), Action.DISCONTINUE);
		Assert.assertEquals(discontinueOrder.getOrderReason(), concept);
		Assert.assertEquals(discontinueOrder.getPreviousOrder(), order);
	}