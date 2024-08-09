	@Test
	public void unretireOrderType_shouldUnretireOrderType() {
		OrderType orderType = orderService.getOrderType(16);
		assertTrue(orderType.getRetired());
		assertNotNull(orderType.getRetiredBy());
		assertNotNull(orderType.getRetireReason());
		assertNotNull(orderType.getDateRetired());
		orderService.unretireOrderType(orderType);
		orderType = orderService.getOrderType(16);
		assertFalse(orderType.getRetired());
		assertNull(orderType.getRetiredBy());
		assertNull(orderType.getRetireReason());
		assertNull(orderType.getDateRetired());
	}