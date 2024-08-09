@Test
	public void unvoidOrder_shouldFailForADiscontinuationOrderIfThePreviousOrderIsInactive() throws Exception {
		Order order = orderService.getOrder(22);
		assertEquals(Action.DISCONTINUE, order.getAction());
		Order previousOrder = order.getPreviousOrder();
		assertNotNull(previousOrder.getDateStopped());
		assertFalse(order.getVoided());
		
		//void the DC order for testing purposes so we can unvoid it later
		orderService.voidOrder(order, "None");
		assertTrue(order.getVoided());
		assertNull(previousOrder.getDateStopped());
		
		//stop the order with a different DC order
		orderService.discontinueOrder(previousOrder, "Testing", null, previousOrder.getOrderer(), previousOrder
		        .getEncounter());
		Thread.sleep(10);
		
		expectedException.expect(APIException.class);
		expectedException.expectMessage("Cannot unvoid a discontinuation order if the previous order is no longer active");
		orderService.unvoidOrder(order);
	}