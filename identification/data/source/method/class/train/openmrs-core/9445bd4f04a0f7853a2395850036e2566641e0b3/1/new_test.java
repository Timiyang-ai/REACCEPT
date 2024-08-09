	@Test
	public void isType_shouldTrueIfItIsTheSameOrIsASubtype() throws Exception {
		Order order = new Order();
		OrderType orderType = new OrderType();
		OrderType subType1 = new OrderType();
		OrderType subType2 = new OrderType();
		subType2.setParent(subType1);
		subType1.setParent(orderType);
		order.setOrderType(subType2);
		
		assertTrue(order.isType(subType2));
		assertTrue(order.isType(subType1));
		assertTrue(order.isType(orderType));
	}