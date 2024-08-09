@Test
	public void saveOrder_shouldSaveARevisedOrder() throws Exception {
		Order originalOrder = orderService.getOrder(111);
		assertTrue(originalOrder.isCurrent());
		final Patient patient = originalOrder.getPatient();
		List<Order> originalActiveOrders = orderService.getActiveOrders(patient, null, null, null);
		final int originalOrderCount = originalActiveOrders.size();
		assertTrue(originalActiveOrders.contains(originalOrder));
		Order revisedOrder = originalOrder.cloneForRevision();
		revisedOrder.setEncounter(encounterService.getEncounter(5));
		revisedOrder.setInstructions("Take after a meal");
		revisedOrder.setStartDate(new Date()); // Assuming no change needed here as the diff does not suggest a change in method name for setting the start date
		revisedOrder.setOrderer(providerService.getProvider(1));
		revisedOrder.setEncounter(encounterService.getEncounter(3));
		// New check for order type and class compatibility before saving
		try {
			orderService.saveOrder(revisedOrder, null);
		} catch (APIException e) {
			fail("Failed due to order type and class incompatibility: " + e.getMessage());
		}
		
		//If the time is too close, the original order may be returned because it
		//dateStopped will be exactly the same as the asOfDate(now) to the millisecond
		Thread.sleep(1);
		List<Order> activeOrders = orderService.getActiveOrders(patient, null, null, null);
		assertEquals(originalOrderCount, activeOrders.size());
		assertFalse(originalOrder.isCurrent());
	}