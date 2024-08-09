@Test
	@Verifies(value = "should discontinue and return the old order", method = "discontinueOrder(Order,String,User,Date)")
	public void discontinueOrder_shouldDiscontinueAndReturnTheOldOrder() throws Exception {
		int originalCount = service.getOrders(Order.class, null, null, null, null, null, null, null).size();
		Order order = service.getOrder(3);
		
		Assert.assertFalse(order.getDiscontinued());
		Assert.assertNull(order.getDiscontinuedDate());
		Assert.assertNull(order.getDiscontinuedBy());
		Assert.assertNull(order.getDiscontinuedReason());
		Order returnedOrder = service.discontinueOrder(order, "Testing");
		Assert.assertEquals(order, returnedOrder);
		Assert.assertTrue(order.getDiscontinued());
		Assert.assertEquals("Testing", returnedOrder.getDiscontinuedReason());
		Assert.assertNotNull(returnedOrder.getDiscontinuedDate());
		Assert.assertNotNull(returnedOrder.getDiscontinuedBy());
		//should have created a discontinue order
		Assert.assertEquals(originalCount + 1, service.getOrders(Order.class, null, null, null, null, null, null, null)
		        .size());
		//find the newly created order and make ensure that its action is DISCONTINUE
		Order discontinueOrder = null;
		for (Order o : service.getOrders(Order.class, null, null, null, null, null, null, null)) {
			if (OpenmrsUtil.nullSafeEquals(o.getPreviousOrderNumber(), order.getOrderNumber()))
				discontinueOrder = o;
		}
		
		Assert.assertNotNull(discontinueOrder);
		Assert.assertEquals(OrderAction.DISCONTINUE, discontinueOrder.getOrderAction());
	}