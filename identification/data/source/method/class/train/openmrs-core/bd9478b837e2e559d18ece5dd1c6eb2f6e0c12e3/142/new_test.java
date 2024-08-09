	@Test
	public void getOrderTypes_shouldGetAllOrderTypesIfIncludeRetiredIsSetToTrue() {
		assertEquals(14, orderService.getOrderTypes(true).size());
	}