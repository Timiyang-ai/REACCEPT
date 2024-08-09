@Test
	@Verifies(value = "shoud return unretired order subtypes of given order type", method = "getOrderSubtypes(org.openmrs.OrderType, boolean)")
	public void getOrderSubTypes_shouldGetAllSubOrderTypesWithoutRetiredOrderTypes() {
		OrderService orderService = Context.getOrderService();
		List<OrderType> orderTypeList = orderService.getOrderSubtypes(orderService.getOrderType(2), false);
		assertEquals(6, orderTypeList.size());
	}