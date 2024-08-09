	@Test
	public void getOrderTypeByUuid_shouldFindOrderTypeObjectGivenValidUuid() {
		OrderType orderType = orderService.getOrderTypeByUuid("131168f4-15f5-102d-96e4-000c29c2a5d7");
		assertEquals("Drug order", orderType.getName());
	}