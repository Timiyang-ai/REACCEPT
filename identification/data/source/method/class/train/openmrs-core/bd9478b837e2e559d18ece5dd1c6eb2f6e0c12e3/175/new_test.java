	@Test
	public void unvoidOrder_shouldUnvoidAnOrder() {
		Order order = orderService.getOrder(8);
		assertTrue(order.getVoided());
		assertNotNull(order.getDateVoided());
		assertNotNull(order.getVoidedBy());
		assertNotNull(order.getVoidReason());
		
		orderService.unvoidOrder(order);
		assertFalse(order.getVoided());
		assertNull(order.getDateVoided());
		assertNull(order.getVoidedBy());
		assertNull(order.getVoidReason());
	}