@Test
	@Verifies(value = "should return empty list for concept without orders", method = "getOrderHistoryByConcept(Concept)")
	public void getOrderHistoryByConcept_shouldReturnEmptyListForConceptWithoutOrders() throws Exception {
		Concept concept = Context.getConceptService().getConcept(21);
		List<Order> orders = Context.getOrderService().getOrderHistoryByConcept(concept);
		Assert.assertEquals(0, orders.size());
	}