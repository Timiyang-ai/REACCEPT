@Test
	@Verifies(value = "should return orders with the given concept", method = "getOrderHistoryByConcept(Patient,Concept)")
	public void getOrderHistoryByConcept_shouldReturnOrdersWithTheGivenConcept() throws Exception {
		//We should have two orders with this concept.
		Concept concept = Context.getConceptService().getConcept(88);
		Patient patient = Context.getPatientService().getPatient(2);
		List<Order> orders = Context.getOrderService().getOrderHistoryByConcept(patient, concept);
		Assert.assertEquals(2, orders.size());
		for (Order order : orders)
			Assert.assertTrue(order.getOrderId() == 4 || order.getOrderId() == 5);
		
		//We should two different orders with this concept
		concept = Context.getConceptService().getConcept(792);
		orders = Context.getOrderService().getOrderHistoryByConcept(patient, concept);
		Assert.assertEquals(2, orders.size());
		for (Order order : orders)
			Assert.assertTrue(order.getOrderId() == 2 || order.getOrderId() == 3);
	}