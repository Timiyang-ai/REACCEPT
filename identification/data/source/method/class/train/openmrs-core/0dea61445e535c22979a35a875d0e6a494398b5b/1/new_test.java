@Test
	@Verifies(value = "should return all order history for given order number", method = "getOrderHistoryByOrderNumber(String)")
	public void getOrderHistoryByOrderNumber_shouldReturnAllOrderHistoryForGivenOrderNumber() throws Exception {
		List<Order> orders = Context.getOrderService().getOrderHistoryByOrderNumber("111");
		Assert.assertEquals(2, orders.size());
		Assert.assertEquals(111, orders.get(0).getOrderId().intValue());
		Assert.assertEquals(1, orders.get(1).getOrderId().intValue());
	}