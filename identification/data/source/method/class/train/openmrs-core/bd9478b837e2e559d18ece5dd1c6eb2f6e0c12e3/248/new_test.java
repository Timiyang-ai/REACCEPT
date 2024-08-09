	@Test
	public void voidOrder_shouldVoidAnOrder() {
		Order order = orderService.getOrder(1);
		assertFalse(order.getVoided());
		assertNull(order.getDateVoided());
		assertNull(order.getVoidedBy());
		assertNull(order.getVoidReason());
		
		orderService.voidOrder(order, "None");
		assertTrue(order.getVoided());
		assertNotNull(order.getDateVoided());
		assertNotNull(order.getVoidedBy());
		assertNotNull(order.getVoidReason());
	}