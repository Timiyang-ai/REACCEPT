	@Test
	public void getOrderTypeByName_shouldReturnTheOrderTypeThatMatchesTheSpecifiedName() {
		OrderType orderType = orderService.getOrderTypeByName("Drug order");
		assertEquals("131168f4-15f5-102d-96e4-000c29c2a5d7", orderType.getUuid());
	}