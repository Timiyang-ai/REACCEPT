@Test
	@Verifies(value = "should fill order with non user", method = "fillOrder(Order, User)")
	public void fillOrder_shouldFillGivenOrderWithNonUser() throws Exception {
		User provider = Context.getUserService().getUser(501);
		
		Order order = Context.getOrderService().getOrder(10);
		Assert.assertTrue(order.getFiller() == null);
		Assert.assertTrue(order.getDateFilled() == null);
		
		Context.getOrderService().signOrder(order, provider);
		Context.getOrderService().fillOrder(order, "url");
		
		order = Context.getOrderService().getOrder(10);
		Assert.assertTrue(order.getFiller() != null);
		Assert.assertTrue(order.getDateFilled() != null);
	}