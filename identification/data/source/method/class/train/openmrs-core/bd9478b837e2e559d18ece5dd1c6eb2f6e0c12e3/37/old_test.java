	@Test
	public void getOrderType_shouldFindOrderTypeObjectGivenValidId() {
		assertEquals("Drug order", orderService.getOrderType(1).getName());
	}