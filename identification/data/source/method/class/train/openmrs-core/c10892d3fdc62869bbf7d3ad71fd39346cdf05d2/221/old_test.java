	@Test
	public void retireOrderType_shouldRetireOrderType() {
		OrderType orderType = orderService.getOrderType(15);
		assertFalse(orderType.getRetired());
		assertNull(orderType.getRetiredBy());
		assertNull(orderType.getRetireReason());
		assertNull(orderType.getDateRetired());
		orderService.retireOrderType(orderType, "Retire for testing purposes");
		orderType = orderService.getOrderType(15);
		assertTrue(orderType.getRetired());
		assertNotNull(orderType.getRetiredBy());
		assertNotNull(orderType.getRetireReason());
		assertNotNull(orderType.getDateRetired());
	}