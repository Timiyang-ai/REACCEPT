@Test
	@Verifies(value = "should not allow deleting an order type that is in use", method = "purgeOrderType(org.openmrs.OrderType)")
	public void purgeOrderType_shouldNotAllowDeletingAnOrderTypeThatIsInUse() {
		OrderType orderType = orderService.getOrderType(1);
		assertNotNull(orderType);
		expectedException.expect(CannotDeleteOrderPropertyInUseException.class);
		expectedException.expectMessage("Order.type.cannot.delete");
		orderService.purgeOrderType(orderType);
	}