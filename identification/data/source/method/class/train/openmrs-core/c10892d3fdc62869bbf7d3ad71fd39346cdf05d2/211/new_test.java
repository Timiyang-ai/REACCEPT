	@Test
	public void saveOrderType_shouldAddANewOrderTypeToTheDatabase() {
		int orderTypeCount = orderService.getOrderTypes(true).size();
		OrderType orderType = new OrderType();
		orderType.setName("New Order");
		orderType.setJavaClassName("org.openmrs.NewTestOrder");
		orderType.setDescription("New order type for testing");
		orderType.setRetired(false);
		orderType = orderService.saveOrderType(orderType);
		assertNotNull(orderType);
		assertEquals("New Order", orderType.getName());
		assertNotNull(orderType.getId());
		assertEquals((orderTypeCount + 1), orderService.getOrderTypes(true).size());
	}