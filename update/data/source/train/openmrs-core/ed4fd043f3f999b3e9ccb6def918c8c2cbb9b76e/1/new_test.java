@Test(expected = APIException.class)
	public void discontinueOrder_shouldFailForAStoppedOrder() throws Exception {
		Order orderToDiscontinue = orderService.getOrder(1);
		Encounter encounter = encounterService.getEncounter(3);
		assertNotNull(orderToDiscontinue.getDateStopped());
		orderService.discontinueOrder(orderToDiscontinue, Context.getConceptService().getConcept(1), null, null, encounter);
	}