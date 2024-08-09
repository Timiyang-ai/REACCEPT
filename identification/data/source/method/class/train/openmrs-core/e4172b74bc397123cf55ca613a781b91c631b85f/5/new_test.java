	@Test
	public void getOrderByOrderNumber_shouldFindObjectGivenValidOrderNumber() {
		Order order = orderService.getOrderByOrderNumber("1");
		Assert.assertNotNull(order);
		Assert.assertEquals(1, (int) order.getOrderId());
	}