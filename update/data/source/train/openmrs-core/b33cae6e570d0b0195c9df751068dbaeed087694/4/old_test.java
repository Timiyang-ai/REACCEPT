@Test
	public void unvoidOrder_shouldFailForAReviseOrderIfThePreviousOrderIsInactive() throws Exception {
		Order order = orderService.getOrder(111);
		assertEquals(Action.REVISE, order.getAction());
		Order previousOrder = order.getPreviousOrder();
		assertNotNull(previousOrder.getDateStopped());
		assertFalse(order.getVoided());
		
		//void the DC order for testing purposes so we can unvoid it later
		orderService.voidOrder(order, "None");
		assertTrue(order.getVoided());
		assertNull(previousOrder.getDateStopped());
		
		//stop the order with a different REVISE order
		Order revise = previousOrder.cloneForRevision();
		revise.setOrderer(order.getOrderer());
		revise.setEncounter(order.getEncounter());
		orderService.saveOrder(revise, null);
		Thread.sleep(10);
		
		expectedException.expect(APIException.class);
		expectedException.expectMessage(
		    Context.getMessageSourceService().getMessage("Order.action.cannot.unvoid", new Object[] { "revision" }, null));
		orderService.unvoidOrder(order);
	}