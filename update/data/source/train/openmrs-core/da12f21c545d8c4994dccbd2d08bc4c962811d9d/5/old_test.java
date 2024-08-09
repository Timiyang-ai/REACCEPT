@Test
	@Verifies(value = "should not allow deleting an order type that is in use", method = "purgeOrderType(org.openmrs.OrderType)")
	public void purgeOrderType_shouldNotAllowDeletingAnOrderTypeThatIsInUse() {
		OrderType orderType = orderService.getOrderType(1);
		assertNotNull(orderType);
		expectedException.expect(APIException.class);
		expectedException.expectMessage("This order type cannot be deleted because it is already in use");
		orderService.purgeOrderType(orderType);
	}