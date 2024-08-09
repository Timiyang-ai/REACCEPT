	@Test
	public void getOrderHistoryByConcept_shouldReturnOrdersWithTheGivenConcept() {
		//We should have two orders with this concept.
		Concept concept = Context.getConceptService().getConcept(88);
		Patient patient = Context.getPatientService().getPatient(2);
		List<Order> orders = orderService.getOrderHistoryByConcept(patient, concept);
		
		//They must be sorted by dateActivated starting with the latest
		Assert.assertEquals(3, orders.size());
		Assert.assertEquals(444, orders.get(0).getOrderId().intValue());
		Assert.assertEquals(44, orders.get(1).getOrderId().intValue());
		Assert.assertEquals(4, orders.get(2).getOrderId().intValue());
		
		concept = Context.getConceptService().getConcept(792);
		orders = orderService.getOrderHistoryByConcept(patient, concept);
		
		//They must be sorted by dateActivated starting with the latest
		Assert.assertEquals(4, orders.size());
		Assert.assertEquals(3, orders.get(0).getOrderId().intValue());
		Assert.assertEquals(222, orders.get(1).getOrderId().intValue());
		Assert.assertEquals(22, orders.get(2).getOrderId().intValue());
		Assert.assertEquals(2, orders.get(3).getOrderId().intValue());
	}