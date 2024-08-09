	@Test
	public void getOrderByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "921de0a3-05c4-444a-be03-e01b4c4b9142";
		Order order = orderService.getOrderByUuid(uuid);
		Assert.assertEquals(1, (int) order.getOrderId());
	}