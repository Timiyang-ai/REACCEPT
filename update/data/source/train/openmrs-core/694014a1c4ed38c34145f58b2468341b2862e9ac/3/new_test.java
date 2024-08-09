@Test
	@Verifies(value = "should sign given order", method = "signOrder(Order, User)")
	public void signOrder_shouldSignGivenOrder() throws Exception {
		User provider = Context.getUserService().getUser(501);
		
		Order order = Context.getOrderService().getOrder(10);
		Assert.assertTrue(!order.isSigned());
		Assert.assertTrue(order.getDateSigned() == null);
		
		Context.getOrderService().signOrder(order, provider, null);
		
		order = Context.getOrderService().getOrder(10);
		Assert.assertTrue(order.isSigned());
		Assert.assertTrue(order.getDateSigned() != null);
	}