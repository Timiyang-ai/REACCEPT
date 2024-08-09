	@Test
	public void purgeOrderType_shouldDeleteOrderTypeIfNotInUse() {
		final Integer id = 13;
		OrderType orderType = orderService.getOrderType(id);
		assertNotNull(orderType);
		orderService.purgeOrderType(orderType);
		assertNull(orderService.getOrderType(id));
	}