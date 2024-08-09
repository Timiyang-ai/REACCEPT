@Test
	public void saveOrder_shouldFailIfConceptInPreviousOrderDoesNotMatchThatOfTheRevisedOrder() throws Exception {
		Order previousOrder = orderService.getOrder(7);
		Order order = previousOrder.cloneForRevision();
		order.setDateActivated(new Date());
		order.setOrderer(providerService.getProvider(1));
		order.setEncounter(encounterService.getEncounter(6));
		Concept newConcept = conceptService.getConcept(5089);
		assertFalse(previousOrder.getConcept().equals(newConcept));
		order.setConcept(newConcept);
		
		expectedException.expect(APIException.class);
		expectedException.expectMessage("The orderable of the previous order and the new one order don't match");
		orderService.saveOrder(order, null);
	}