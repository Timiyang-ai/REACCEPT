@Test(expected = IllegalArgumentException.class)
	@Verifies(value = "should fail if reason is empty", method = "voidOrder(Order,String)")
	public void voidOrder_shouldFailIfReasonIsEmpty() throws Exception {
		OrderService orderService = Context.getOrderService();
		
		Order order = orderService.getOrder(2);
		Assert.assertNotNull(order);
		
		String voidReason = "";
		orderService.voidOrder(order, voidReason);
	}