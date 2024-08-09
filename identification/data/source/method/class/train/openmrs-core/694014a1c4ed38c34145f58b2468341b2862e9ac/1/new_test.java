@Test
	@Verifies(value = "should activate given order", method = "activateOrder(Order, User)")
	public void activateOrder_shouldActivateGivenOrder() throws Exception {
		User provider = Context.getUserService().getUser(501);
		
		Order order = Context.getOrderService().getOrder(10);
		Assert.assertTrue(order.getActivatedBy() == null);
		Assert.assertTrue(order.getDateActivated() == null);
		
		Context.getOrderService().activateOrder(order, provider, null);
		
		order = Context.getOrderService().getOrder(10);
		Assert.assertTrue(order.getActivatedBy() != null);
		Assert.assertTrue(order.getDateActivated() != null);
	}