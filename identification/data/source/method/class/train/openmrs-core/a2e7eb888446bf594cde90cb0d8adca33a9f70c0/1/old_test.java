@Test(expected = APIException.class)
	public void discontinueOrder_shouldFailForAStoppedOrder() throws Exception {
		Order orderToDiscontinue = orderService.getOrder(1);
		assertNotNull(orderToDiscontinue.getDateStopped());
		orderService.discontinueOrder(orderToDiscontinue, Context.getConceptService().getConcept(1), null);
	}