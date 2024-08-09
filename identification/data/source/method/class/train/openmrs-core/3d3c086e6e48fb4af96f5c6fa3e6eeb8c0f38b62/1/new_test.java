@Test
	@Verifies(value = "when saving a discontinuedReasonNonCoded parameter the value is correctly stored to the database", method = "saveOrder(Order)")
	public void saveOrder_shouldSaveDiscontinuedReasonNonCoded() throws Exception {
		String uuid = "921de0a3-05c4-444a-be03-e01b4c4b9142";
		Order order = Context.getOrderService().getOrderByUuid(uuid);
		String discontinuedReasonNonCoded = "Non coded discontinued reason";
		
		order.setOrderReasonNonCoded(discontinuedReasonNonCoded);
		OrderService orderService = Context.getOrderService();
		orderService.saveOrder(order);
		
		order = Context.getOrderService().getOrderByUuid(uuid);
		
		Assert.assertEquals(discontinuedReasonNonCoded, order.getOrderReasonNonCoded());
	}