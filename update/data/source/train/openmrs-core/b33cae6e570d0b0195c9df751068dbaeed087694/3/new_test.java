@Test
	public void saveOrder_shouldFailIfTheExistingDrugOrderMatchesTheConceptAndNotDrugOfTheDCOrder() throws Exception {
		final DrugOrder orderToDiscontinue = (DrugOrder) orderService.getOrder(5);
		assertTrue(OrderUtilTest.isActiveOrder(orderToDiscontinue, null));
		
		//create a different test drug
		Drug discontinuationOrderDrug = new Drug();
		discontinuationOrderDrug.setConcept(orderToDiscontinue.getConcept());
		discontinuationOrderDrug = conceptService.saveDrug(discontinuationOrderDrug);
		assertNotEquals(discontinuationOrderDrug, orderToDiscontinue.getDrug());
		assertNotNull(orderToDiscontinue.getDrug());
		
		DrugOrder order = orderToDiscontinue.cloneForRevision();
		order.setDateActivated(new Date());
		order.setOrderer(providerService.getProvider(1));
		order.setEncounter(encounterService.getEncounter(6));
		order.setDrug(discontinuationOrderDrug);
		order.setOrderReasonNonCoded("Discontinue this");
		
		expectedException.expect(EditedOrderDoesNotMatchPreviousException.class);
		expectedException.expectMessage("The orderable of the previous order and the new one order don't match");
		orderService.saveOrder(order, null);
	}